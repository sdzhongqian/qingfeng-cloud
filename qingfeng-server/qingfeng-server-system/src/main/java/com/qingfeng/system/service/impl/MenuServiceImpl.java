package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Dictionary;
import com.qingfeng.entity.system.Menu;
import com.qingfeng.entity.system.Role;
import com.qingfeng.system.mapper.MenuMapper;
import com.qingfeng.system.service.IMenuService;
import com.qingfeng.utils.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName MenuServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:33
 */
@Slf4j
@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:33
     */
    public IPage<Menu> findListPage(Menu menu, QueryRequest request){
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, menu);
    }

    /**
     * @title findAuthMenuList
     * @description 查询权限菜单列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:34
     */
    public List<PageData> findAuthMenuList(PageData pd){
        return this.baseMapper.findUserPermissions(pd);
    }

    /**
     * @ProjectName MenuServiceImpl
     * @author qingfeng
     * @version 1.0.0
     * @Description 查询数据列表
     * @createTime 2021/4/3 0003 21:34
     */
    public List<PageData> findList(PageData pd){
        return this.baseMapper.findList(pd);
    }
}