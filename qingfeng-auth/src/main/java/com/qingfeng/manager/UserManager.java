package com.qingfeng.manager;

import com.qingfeng.mapper.MenuMapper;
import com.qingfeng.mapper.UserMapper;
import com.qingfeng.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName UserManager
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:09
 */
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * @title findUserInfo
     * @description 查询用户详情
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:09
     * @throws
     */
    public PageData findUserInfo(PageData pd) {
        return userMapper.findUserInfo(pd);
    }

    /**
     * @title findUserOrganizeInfo
     * @description 查询用户组织信息
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:10
     * @throws
     */
    public PageData findUserOrganizeInfo(PageData pd) {
        return userMapper.findUserOrganizeInfo(pd);
    }

    /**
     * @title findUserPermissions
     * @description 查询用户权限集合
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:09
     * @throws
     */
    public String findUserPermissions(PageData pd) {
        List<PageData> userPermissions = menuMapper.findUserPermissions(pd);
        return userPermissions.stream().map(perms->perms.get("perms").toString()).collect(Collectors.joining(","));
    }
}
