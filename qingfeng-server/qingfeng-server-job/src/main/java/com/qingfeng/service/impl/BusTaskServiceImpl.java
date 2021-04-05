package com.qingfeng.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.quartz.Bustask;
import com.qingfeng.mapper.BusTaskMapper;
import com.qingfeng.service.IBusTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qingfeng
 * @title: BusTaskServiceImpl
 * @projectName qingfeng-cloud
 * @description: 业务任务
 * @date 2021/3/8 000821:13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BusTaskServiceImpl extends ServiceImpl<BusTaskMapper, Bustask> implements IBusTaskService {


    public IPage<Bustask> findListPage(Bustask bustask, QueryRequest request){
        Page<Bustask> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, bustask);
    }




}