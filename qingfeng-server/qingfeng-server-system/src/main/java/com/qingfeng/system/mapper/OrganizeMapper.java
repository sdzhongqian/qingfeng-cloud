package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Organize;
import com.qingfeng.entity.system.Role;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.utils.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName OrganizeMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:23
 */
@SqlParser(filter=true)
public interface OrganizeMapper extends BaseMapper<Organize> {

    //查询数据分页列表
    IPage<Organize> findListPage(Page page, @Param("obj") Organize organize);

    //查询组织角色列表
    List<Role> findOrganizeRoleList(PageData pd);


    //查询组织列表
    List<Organize> findList(Organize organize);

    //查询树形表格
    List<PageData> findTreeTableList(PageData pd);

    //删除组织角色
    void delOrganizeRole(PageData pd);
}