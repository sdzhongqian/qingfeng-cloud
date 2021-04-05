package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Area;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.entity.system.UserOrganize;
import com.qingfeng.system.mapper.AreaMapper;
import com.qingfeng.system.mapper.UserOrganizeMapper;
import com.qingfeng.system.service.IAreaService;
import com.qingfeng.system.service.IUserOrganizeService;
import com.qingfeng.utils.DateTimeUtil;
import com.qingfeng.utils.GuidUtil;
import com.qingfeng.utils.PageData;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName UserOrganizeServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:37
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserOrganizeServiceImpl extends ServiceImpl<UserOrganizeMapper, UserOrganize> implements IUserOrganizeService {

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:37
     */
    @Override
    public IPage<UserOrganize> findListPage(UserOrganize userOrganize, QueryRequest request) {
        Page<UserOrganize> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, userOrganize);
    }

    /**
     * @title: findUserOrganizeInfo
     * @description: 查询用户组织信息
     * @author: qingfeng
     * @date: 2021/3/11 0011 22:42
     */
    public UserOrganize findUserOrganizeInfo(UserOrganize userOrganize){
        return this.baseMapper.findUserOrganizeInfo(userOrganize);
    }

    /**
     * @title saveUserOrganize
     * @description 保存用户组织信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:37
     */
    public void saveUserOrganize(UserOrganize userOrganize){
        String id = GuidUtil.getUuid();
        String time = DateTimeUtil.getDateTimeStr();
        userOrganize.setId(id);
        userOrganize.setType("1");
        userOrganize.setUse_status("0");
        userOrganize.setOrder_by("1");
        userOrganize.setCreate_time(time);
        //处理数据权限
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        userOrganize.setCreate_user(authParams.split(":")[1]);
        save(userOrganize);
    }

    /**
     * @title updateUserOrganize
     * @description 更新用户组织信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:37
     */
    public void updateUserOrganize(UserOrganize userOrganize){
        // 更新
        String time = DateTimeUtil.getDateTimeStr();
        userOrganize.setUpdate_time(time);
        updateById(userOrganize);
    }

    /**
     * @title findUserOrganize
     * @description 查询用户组织信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:37
     */
    public List<UserOrganize> findUserOrganize(PageData pd){
        return this.baseMapper.findUserOrganize(pd);
    }


}