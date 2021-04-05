package com.qingfeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.quartz.Bustask;
import org.apache.ibatis.annotations.Param;

/**
 * @author qingfeng
 * @title: BusTaskMapper
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:13
 */
public interface BusTaskMapper extends BaseMapper<Bustask> {

    IPage<Bustask> findListPage(Page page, @Param("obj") Bustask bustask);

}