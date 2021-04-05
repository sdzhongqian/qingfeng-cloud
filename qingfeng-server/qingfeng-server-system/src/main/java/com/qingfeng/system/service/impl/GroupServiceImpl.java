package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Group;
import com.qingfeng.entity.system.UserGroup;
import com.qingfeng.system.mapper.GroupMapper;
import com.qingfeng.system.service.IGroupService;
import com.qingfeng.system.service.IUserGroupService;
import com.qingfeng.utils.DateTimeUtil;
import com.qingfeng.utils.GuidUtil;
import com.qingfeng.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName GroupServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:33
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    @Autowired
    public IUserGroupService userGroupService;

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:33
     */
    public IPage<Group> findListPage(Group group, QueryRequest request){
        Page<Group> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, group);
    }

    /**
     * @title findList
     * @description 查询数据列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:33
     */
    public List<Group> findList(Group group){
        return this.baseMapper.findList(group);
    }

    /**
     * @title saveGroup
     * @description 保存数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:33
     */
    public void saveGroup(Group group){
        // 创建用户
        String id = GuidUtil.getUuid();
        group.setId(id);
        String time = DateTimeUtil.getDateTimeStr();
        group.setCreate_time(time);
        group.setStatus("0");
        group.setType("1");
        //处理数据权限
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        group.setCreate_user(authParams.split(":")[1]);
        group.setCreate_organize(authParams.split(":")[2]);
        this.save(group);
        //处理关联用户信息
        String[] user_ids = group.getUser_ids().split(",");
        String[] user_names = group.getUser_names().split(",");
        List<UserGroup> list = new ArrayList<UserGroup>();
        for (int i = 0; i < user_ids.length; i++) {
            UserGroup userGroup = new UserGroup();
            userGroup.setId(GuidUtil.getUuid());
            userGroup.setUser_id(user_ids[i]);
            userGroup.setUser_name(user_names[i]);
            userGroup.setGroup_id(id);
            userGroup.setCreate_user(authParams.split(":")[1]);
            userGroup.setCreate_time(time);
            list.add(userGroup);
        }
        userGroupService.saveBatch(list);
    }

    /**
     * @title updateGroup
     * @description 更新数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:33
     */
    public void updateGroup(Group group){
        // 更新组织信息
        String time = DateTimeUtil.getDateTimeStr();
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        group.setUpdate_time(time);
        group.setUpdate_user(authParams.split(":")[1]);
        this.updateById(group);
        //处理关联用户信息
        String id = group.getId();
        PageData param = new PageData();
        param.put("group_id",id);
        userGroupService.removeByMap(param);
        String[] user_ids = group.getUser_ids().split(",");
        String[] user_names = group.getUser_names().split(",");
        List<UserGroup> list = new ArrayList<UserGroup>();
        for (int i = 0; i < user_ids.length; i++) {
            UserGroup userGroup = new UserGroup();
            userGroup.setId(GuidUtil.getUuid());
            userGroup.setUser_id(user_ids[i]);
            userGroup.setUser_name(user_names[i]);
            userGroup.setGroup_id(id);
            userGroup.setCreate_user(authParams.split(":")[1]);
            userGroup.setCreate_time(time);
            list.add(userGroup);
        }
        userGroupService.saveBatch(list);
    }


}