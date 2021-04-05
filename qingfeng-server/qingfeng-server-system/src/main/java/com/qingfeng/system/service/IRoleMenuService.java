package com.qingfeng.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.system.RoleMenu;
import com.qingfeng.utils.PageData;

import java.util.List;

/**
 * @ProjectName IRoleMenuService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:28
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    //查询数据分页列表
    List<String> findRoleMenuList(PageData pd);

    //查询数据列表
    void delRoleMenu(PageData pd);

}