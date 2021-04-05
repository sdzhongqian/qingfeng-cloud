package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.system.Menu;
import com.qingfeng.utils.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName MenuMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:26
 */
public interface MenuMapper extends BaseMapper<Menu> {

    //查询数据分页列表
    IPage<Menu> findListPage(Page page, @Param("obj") Menu menu);


    /**
     * @title: findAuthMenuList
     * @description: 根据权限查询菜单信息
     * @author: qingfeng
     * @date: 2021/3/9 0009 23:07
     */
    List<PageData> findUserPermissions(PageData pd);

    /**
     * @title: findList
     * @description: 查询菜单列表
     * @author: qingfeng
     * @date: 2021/3/9 0009 23:07
     */
    List<PageData> findList(PageData pd);

}