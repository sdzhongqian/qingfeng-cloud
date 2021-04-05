package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.system.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName GroupMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:26
 */
public interface GroupMapper extends BaseMapper<Group> {

    //查询数据分页列表
    IPage<Group> findListPage(Page page, @Param("obj") Group group);

    //查询数据列表
    List<Group> findList(Group group);

}