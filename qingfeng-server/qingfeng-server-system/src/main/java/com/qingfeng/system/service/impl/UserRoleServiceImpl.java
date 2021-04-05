package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.system.UserRole;
import com.qingfeng.system.mapper.UserRoleMapper;
import com.qingfeng.system.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @ProjectName UserRoleServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:38
 */
@Service("userRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    /**
     * @title deleteUserRolesByRoleId
     * @description 根据角色id删除用户角色信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:38
     */
    @Override
    @Transactional
    public void deleteUserRolesByRoleId(String[] roleIds) {
        Arrays.stream(roleIds).forEach(id -> baseMapper.deleteByRoleId(id));
    }

    /**
     * @title deleteUserRolesByUserId
     * @description 根据用户id删除用户角色信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:38
     */
    @Override
    @Transactional
    public void deleteUserRolesByUserId(String[] userIds) {
        Arrays.stream(userIds).forEach(id -> baseMapper.deleteByUserId(id));
    }
}
