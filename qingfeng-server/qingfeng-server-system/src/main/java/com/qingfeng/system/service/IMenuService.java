package com.qingfeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Dictionary;
import com.qingfeng.entity.system.Menu;
import com.qingfeng.utils.PageData;

import java.util.List;
import java.util.Set;

/**
 * @ProjectName IMenuService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:27
 */
public interface IMenuService extends IService<Menu> {

    //查询数据分页列表
    IPage<Menu> findListPage(Menu menu, QueryRequest request);

    //根据权限查询菜单信息
    List<PageData> findAuthMenuList(PageData pd);

    //查询菜单列表
    List<PageData> findList(PageData pd);


}