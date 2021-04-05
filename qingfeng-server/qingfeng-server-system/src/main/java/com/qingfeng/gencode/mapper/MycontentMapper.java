package com.qingfeng.gencode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.gencode.Mycontent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qingfeng
 * @title: MycontentMapper
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:37
 */
public interface MycontentMapper extends BaseMapper<Mycontent> {

    //查询数据分页列表
    IPage<Mycontent> findListPage(Page page, @Param("obj") Mycontent mycontent);
    //查询数据列表
    List<Mycontent> findList(Mycontent mycontent);

}