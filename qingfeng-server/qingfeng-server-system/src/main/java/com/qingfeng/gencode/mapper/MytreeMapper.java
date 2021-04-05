package com.qingfeng.gencode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.gencode.Mytree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qingfeng
 * @title: MytreeMapper
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:37
 */
public interface MytreeMapper extends BaseMapper<Mytree> {

    //查询数据分页列表
    IPage<Mytree> findListPage(Page page, @Param("obj") Mytree mytree);

    //查询数据列表
    List<Mytree> findList(Mytree mytree);

}