package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingfeng.entity.system.RoleMenu;
import com.qingfeng.utils.PageData;

import java.util.List;

/**
 * @ProjectName RoleMenuMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:24
 */
@SqlParser(filter=true)
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    //查询角色菜单列表
    List<String> findRoleMenuList(PageData pd);

    //删除角色菜单
    void delRoleMenu(PageData pd);

}