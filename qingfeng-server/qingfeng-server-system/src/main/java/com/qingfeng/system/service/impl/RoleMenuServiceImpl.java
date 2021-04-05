package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.system.RoleMenu;
import com.qingfeng.system.mapper.RoleMenuMapper;
import com.qingfeng.system.service.IRoleMenuService;
import com.qingfeng.utils.PageData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName RoleMenuServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:35
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    /**
     * @title findRoleMenuList
     * @description 查询角色菜单
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:35
     */
    public List<String> findRoleMenuList(PageData pd){
        return this.baseMapper.findRoleMenuList(pd);
    }

    /**
     * @title delRoleMenu
     * @description 删除角色菜单
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:36
     */
    public void delRoleMenu(PageData pd){
        this.baseMapper.delRoleMenu(pd);
    }

}