package com.qingfeng.gencode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.gencode.Mycontent;

import java.util.List;

/**
 * @author qingfeng
 * @title: IMycontentService
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:12
 */
public interface IMycontentService extends IService<Mycontent> {

    //查询数据分页列表
    IPage<Mycontent> findListPage(Mycontent mycontent, QueryRequest request);

    //查询数据列表
    List<Mycontent> findList(Mycontent mycontent);

    //保存数据
    void saveMycontent(Mycontent mycontent);

    //更新数据
    void updateMycontent(Mycontent mycontent);

}