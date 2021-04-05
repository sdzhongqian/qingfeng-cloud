package com.qingfeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Area;
import com.qingfeng.entity.system.Dictionary;

import java.util.List;

/**
 * @ProjectName IAreaService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:26
 */
public interface IAreaService extends IService<Area> {

    //查询数据分页列表
    IPage<Area> findListPage(Area area, QueryRequest request);

    //查询数据列表
    List<Area> findList(Area area);

}