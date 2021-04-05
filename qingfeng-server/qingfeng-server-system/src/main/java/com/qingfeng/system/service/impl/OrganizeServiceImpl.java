package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Organize;
import com.qingfeng.entity.system.OrganizeRole;
import com.qingfeng.entity.system.Role;
import com.qingfeng.entity.system.UserRole;
import com.qingfeng.system.mapper.OrganizeMapper;
import com.qingfeng.system.service.IOrganizeRoleService;
import com.qingfeng.system.service.IOrganizeService;
import com.qingfeng.system.service.IUserRoleService;
import com.qingfeng.utils.DateTimeUtil;
import com.qingfeng.utils.GuidUtil;
import com.qingfeng.utils.PageData;
import com.qingfeng.utils.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName OrganizeServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:34
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrganizeServiceImpl extends ServiceImpl<OrganizeMapper, Organize> implements IOrganizeService {

    @Autowired
    private IOrganizeRoleService organizeRoleService;

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:34
     */
    public IPage<Organize> findListPage(Organize organize, QueryRequest request){
        Page<Organize> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, organize);
    }

    /**
     * @title findOrganizeRoleList
     * @description 查询组织角色列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:35
     */
    public List<Role> findOrganizeRoleList(PageData pd){
        return this.baseMapper.findOrganizeRoleList(pd);
    }

    /**
     * @title: findList
     * @description: 查询列表
     * @author: qingfeng
     * @date: 2021/3/11 0011 9:02
     */
    public List<Organize> findList(Organize organize){
        return this.baseMapper.findList(organize);
    }

    /**
     * @title findTreeTableList
     * @description 查询数据权限
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:35
     */
    public List<PageData> findTreeTableList(PageData pd){
        return this.baseMapper.findTreeTableList(pd);
    }

    /**
     * @title updateAuth
     * @description 更新权限
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:35
     */
    public void updateAuth(PageData pd){
        String time = DateTimeUtil.getDateTimeStr();
        String[] role_ids = pd.get("role_ids").toString().split(",");
        //处理数据权限
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        String create_user = userParams.split(":")[1];
        //删除用户角色表。
        pd.put("organize_id",pd.get("id"));
        pd.put("role_ids", new ArrayList<String>());
        this.baseMapper.delOrganizeRole(pd);
        if(Verify.verifyIsNotNull(pd.get("role_ids"))){
            String organize_id = pd.get("id").toString();
            List<OrganizeRole> list = new ArrayList<OrganizeRole>();
            //执行保存
            for (int i = 0; i < role_ids.length; i++) {
                OrganizeRole organizeRole = new OrganizeRole();
                //主键id
                organizeRole.setId(GuidUtil.getUuid());
                organizeRole.setRole_id(role_ids[i]);
                organizeRole.setOrganize_id(organize_id);
                organizeRole.setCreate_time(time);
                organizeRole.setCreate_user(create_user);
                list.add(organizeRole);
            }
            organizeRoleService.saveBatch(list);
        }
    }



}