package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.system.Area;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.entity.system.UserOrganize;
import com.qingfeng.utils.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName UserOrganizeMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:25
 */
public interface UserOrganizeMapper extends BaseMapper<UserOrganize> {

    //查询数据分页列表
    IPage<UserOrganize> findListPage(Page page, @Param("obj") UserOrganize userOrganize);

    //查询用户组织详情
    UserOrganize findUserOrganizeInfo(UserOrganize userOrganize);

    //查询用户组织信息
    List<UserOrganize> findUserOrganize(PageData pd);

}