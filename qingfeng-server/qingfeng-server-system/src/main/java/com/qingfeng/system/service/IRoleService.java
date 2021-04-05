package com.qingfeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Organize;
import com.qingfeng.entity.system.Role;
import com.qingfeng.utils.PageData;

import java.util.List;

/**
 * @ProjectName IRoleService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:28
 */
public interface IRoleService extends IService<Role> {

    //查询数据分页列表
    IPage<Role> findListPage(Role role, QueryRequest request);

    //查询数据列表
    List<Role> findSimpleList(PageData pd);

    //查询数据列表
    List<Role> findList(Role role);

}