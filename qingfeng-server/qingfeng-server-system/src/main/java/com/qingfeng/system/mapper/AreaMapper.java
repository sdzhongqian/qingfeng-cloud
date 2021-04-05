package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.system.Area;
import com.qingfeng.entity.system.Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName AreaMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:22
 */
public interface AreaMapper extends BaseMapper<Area> {

    //查询数据分页列表
    IPage<Area> findListPage(Page page, @Param("obj") Area area);

    //查询数据列表
    List<Area> findList(Area area);

}