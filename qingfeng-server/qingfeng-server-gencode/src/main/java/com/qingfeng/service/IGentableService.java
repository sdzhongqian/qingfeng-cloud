package com.qingfeng.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.gencode.Gentable;
import com.qingfeng.utils.PageData;

import java.util.List;

/**
 * @author qingfeng
 * @title: IGentableService
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:12
 */
public interface IGentableService extends IService<Gentable> {

    //查询数据表分页
    List<PageData> findTableListPage(PageData pd);

    //查询数据表数量
    Integer findTableListNum(PageData pd);

    //查询数据表列表
    List<PageData> findTableList(PageData pd);

    //保存数据表
    void saveTable(PageData pd);

    //查询数据分页列表
    IPage<Gentable> findListPage(Gentable gentable, QueryRequest request);

    //删除
    void del(String ids);

    //更新数据表
    void updateTable(Gentable gentable);

    //查询菜单详情
    PageData findMenuInfo(PageData pd);

}