package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Role;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.utils.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName RoleMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:24
 */
public interface RoleMapper extends BaseMapper<Role> {

    //查询数据分页列表
    IPage<Role> findListPage(Page page, @Param("obj") Role role);

    //查询数据列表
    List<Role> findSimpleList(PageData pd);

}