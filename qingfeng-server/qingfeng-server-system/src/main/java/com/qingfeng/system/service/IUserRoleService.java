package com.qingfeng.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.system.UserRole;

/**
 * @ProjectName IUserRoleService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:30
 */
public interface IUserRoleService extends IService<UserRole> {

    //根据角色id删除用户角色信息
    void deleteUserRolesByRoleId(String[] roleIds);

    //根据用户id删除用户角色信息
    void deleteUserRolesByUserId(String[] userIds);
}