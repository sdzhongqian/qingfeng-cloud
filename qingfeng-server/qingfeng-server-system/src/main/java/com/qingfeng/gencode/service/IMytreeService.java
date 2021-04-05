package com.qingfeng.gencode.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.gencode.Mytree;

import java.util.List;

/**
 * @author qingfeng
 * @title: IMytreeService
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:12
 */
public interface IMytreeService extends IService<Mytree> {

    IPage<Mytree> findListPage(Mytree mytree, QueryRequest request);

    List<Mytree> findList(Mytree mytree);

    void saveMytree(Mytree mytree);

    void updateMytree(Mytree mytree);

}