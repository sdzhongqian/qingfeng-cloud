package com.qingfeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Role;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProjectName IUserService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:30
 */
public interface IUserService extends IService<SystemUser> {

    //查询数据分页列表
    IPage<SystemUser> findListPage(SystemUser user, QueryRequest request);

    //查询数据列表
    List<SystemUser> findList(SystemUser user);

    //新增用户
    void createUser(SystemUser user);

    //修改用户
    void updateUser(SystemUser user);

    //删除用户
    void deleteUsers(String[] userIds);

    //查询用户详情
    PageData findUserInfo(PageData pd);

    //查询用户角色信息
    List<Role> findUserRoleList(PageData pd);

    //查询用户组织信息
    PageData findUserOrganizeInfo(PageData pd);

    //更新密码
    void updatePwd(SystemUser user);

    //更新权限
    void updateAuth(PageData pd);

    //更新用户组织状态
    void updateUserOrgUseStatus(PageData pd);


}
