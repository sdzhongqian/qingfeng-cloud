package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.system.Role;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.utils.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName UserMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:24
 */
public interface UserMapper extends BaseMapper<SystemUser> {

    //查询数据分页列表
    IPage<SystemUser> findListPage(Page page, @Param("user") SystemUser user);

    //查询数据列表
    List<SystemUser> findList(SystemUser user);


    //查询用户详情
    PageData findUserInfo(PageData pd);

    //询用户角色信息
    List<Role> findUserRoleList(PageData pd);

    //查询用户组织信息
    PageData findUserOrganizeInfo(PageData pd);

    //删除用户角色
    void delUserRole(PageData pd);

    //更新用户组织状态
    void updateUserOrgUseStatus(PageData pd);

}