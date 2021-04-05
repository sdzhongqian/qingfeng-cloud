package com.qingfeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.quartz.TimTask;
import org.apache.ibatis.annotations.Param;

/**
 * @author qingfeng
 * @title: TimTaskMapper
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:13
 */
public interface TimTaskMapper extends BaseMapper<TimTask> {

    IPage<TimTask> findListPage(Page page, @Param("obj") TimTask timTask);


}