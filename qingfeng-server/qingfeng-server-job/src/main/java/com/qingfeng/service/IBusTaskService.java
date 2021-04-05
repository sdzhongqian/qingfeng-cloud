package com.qingfeng.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.quartz.Bustask;

/**
 * @ProjectName IBusTaskService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 20:29
 */
public interface IBusTaskService extends IService<Bustask> {

    IPage<Bustask> findListPage(Bustask bustask, QueryRequest request);

}