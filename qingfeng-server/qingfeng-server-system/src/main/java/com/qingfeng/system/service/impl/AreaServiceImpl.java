package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Area;
import com.qingfeng.entity.system.Role;
import com.qingfeng.system.mapper.AreaMapper;
import com.qingfeng.system.service.IAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName AreaServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:32
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:32
     */
    public IPage<Area> findListPage(Area area, QueryRequest request){
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, area);
    }

    /**
     * @title findList
     * @description 查询数据列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:32
     */
    public List<Area> findList(Area area){
        return this.baseMapper.findList(area);
    }



}