package com.qingfeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Area;
import com.qingfeng.entity.system.Organize;
import com.qingfeng.entity.system.Role;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.utils.PageData;

import java.util.List;

/**
 * @ProjectName IOrganizeService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:27
 */
public interface IOrganizeService extends IService<Organize> {

    //查询数据分页列表
    IPage<Organize> findListPage(Organize organize, QueryRequest request);

    //查询组织角色列表
    List<Role> findOrganizeRoleList(PageData pd);

    //查询数据列表
    List<Organize> findList(Organize organize);

    //查询数据表
    List<PageData> findTreeTableList(PageData pd);

    //更新权限
    void updateAuth(PageData pd);

}