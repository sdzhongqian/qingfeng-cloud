package com.qingfeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.gencode.Genfield;
import com.qingfeng.utils.PageData;

import java.util.List;

/**
 * @author qingfeng
 * @title: IGenfieldService
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:12
 */
public interface IGenfieldService extends IService<Genfield> {

    //查询字段列表
    List<Genfield> findFieldList(PageData pd);


}