package com.qingfeng.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.quartz.TimTask;

/**
 * @author qingfeng
 * @title: IRoleService
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:12
 */
public interface ITimTaskService extends IService<TimTask> {

    IPage<TimTask> findListPage(TimTask timTask, QueryRequest request);


}