package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.common.service.IUploadService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.common.UploadFile;
import com.qingfeng.entity.system.Role;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.entity.system.UserOrganize;
import com.qingfeng.entity.system.UserRole;
import com.qingfeng.system.mapper.UserMapper;
import com.qingfeng.system.service.IUserGroupService;
import com.qingfeng.system.service.IUserOrganizeService;
import com.qingfeng.system.service.IUserRoleService;
import com.qingfeng.system.service.IUserService;
import com.qingfeng.utils.*;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ProjectName UserServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:38
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserOrganizeService userOrganizeService;
    @Autowired
    private IUploadService uploadService;
    @Autowired
    private IUserGroupService userGroupService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @title findListPage
     * @description 查询信息分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:38
     */
    @Override
    public IPage<SystemUser> findListPage(SystemUser user, QueryRequest request) {
        Page<SystemUser> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, user);
    }

    /**
     * @title findList
     * @description 查询数据列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:39
     */
    @Override
    public List<SystemUser> findList(SystemUser user){
        List<SystemUser> list = this.baseMapper.findList(user);
        return list;
    }

    /**
     * @title createUser
     * @description 保存信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:39
     */
    @Override
    @Transactional
    public void createUser(SystemUser user) {
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("login_name","admin");
//        Collection list = listByMap(columnMap);
//        System.out.println("################");
//        System.out.println(list.size());
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("login_name","admin");
//        List ls = list(queryWrapper);
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$");
//        System.out.println(ls.size());
//        System.out.println(JsonToMap.list2json(ls));
        // 创建用户
        String id = GuidUtil.getUuid();
        user.setId(id);
        String time = DateTimeUtil.getDateTimeStr();
        user.setCreate_time(time);
        user.setStatus("0");
        user.setType("1");
        user.setPwd_error_num("0");
        user.setLogin_password(passwordEncoder.encode(user.getLogin_password()));
        //处理数据权限
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        user.setCreate_user(authParams.split(":")[1]);
        user.setCreate_organize(authParams.split(":")[2]);
        save(user);
        //保存用户组织信息
        UserOrganize userOrganize = new UserOrganize();
        userOrganize.setId(GuidUtil.getUuid());
        userOrganize.setUser_id(id);
        userOrganize.setType("0");
        userOrganize.setUse_status("0");
        userOrganize.setOrganize_id(user.getOrganize_id());
        userOrganize.setOrganize_name(user.getOrganize_name());
        userOrganize.setOrder_by("1");
        userOrganize.setCreate_user(authParams.split(":")[1]);
        userOrganize.setCreate_time(time);
        userOrganizeService.save(userOrganize);
        //处理附件
        if(Verify.verifyIsNotNull(user.getFileIds())){
            UploadFile uploadFile = new UploadFile();
            uploadFile.setObj_id(id);
            uploadFile.setUpdate_time(time);
            String fileIds[] = user.getFileIds().split(",");
            for (int j = 0; j < fileIds.length; j++) {
                uploadFile.setId(fileIds[j]);
                uploadService.updateById(uploadFile);
            }
        }

    }

    /**
     * @title updateUser
     * @description 更新信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:39
     */
    @Override
    @Transactional
    public void updateUser(SystemUser user) {
        // 更新用户
        String time = DateTimeUtil.getDateTimeStr();
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        user.setUpdate_time(time);
        user.setUpdate_user(authParams.split(":")[1]);
        updateById(user);
        //处理附件
        if(Verify.verifyIsNotNull(user.getFileIds())){
            UploadFile uploadFile = new UploadFile();
            uploadFile.setObj_id(user.getId());
            uploadFile.setUpdate_time(time);
            String fileIds[] = user.getFileIds().split(",");
            for (int j = 0; j < fileIds.length; j++) {
                uploadFile.setId(fileIds[j]);
                uploadService.updateById(uploadFile);
            }
        }

//        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
//        String[] roles = user.getRoleId().split(StringPool.COMMA);
//        setUserRoles(user, roles);
    }

    /**
     * @title deleteUsers
     * @description 生成信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:39
     */
    @Override
    @Transactional
    public void deleteUsers(String[] ids) {
        List<String> list = Arrays.asList(ids);
        removeByIds(list);
        //删除关联组织
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("user_id",ids);
        userOrganizeService.remove(queryWrapper);
        //删除组用户关联表
        userGroupService.remove(queryWrapper);
        //删除用户角色关联
        userRoleService.remove(queryWrapper);
    }

    /**
     * @title updatePwd
     * @description 更新密码
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:39
     */
    @Transactional
    public void updatePwd(SystemUser user) {
        String[] ids = user.getIds().split(",");
        List<SystemUser> list = new ArrayList<SystemUser>();
        for (String id:ids) {
            SystemUser u = new SystemUser();
            u.setId(id);
            u.setLogin_password(passwordEncoder.encode(user.getLogin_password()));
            u.setUpdate_time(DateTimeUtil.getDateTimeStr());
            list.add(u);
            System.out.println("##:"+JsonToMap.bean2json(u));
        }
        updateBatchById(list);
    }



    /**
     * 查询用户详情
     * @param pd
     * @return
     */
    public PageData findUserInfo(PageData pd){
        return this.baseMapper.findUserInfo(pd);
    }

    /**
     * 查询用户角色信息
     * @param pd
     * @return
     */
    public List<Role> findUserRoleList(PageData pd){
        return this.baseMapper.findUserRoleList(pd);
    }

    /**
     * 查询用户组织信息
     * @param pd
     * @return
     */
    public PageData findUserOrganizeInfo(PageData pd){
        return this.baseMapper.findUserOrganizeInfo(pd);
    }

    /**
     * @title updateAuth
     * @description 更新权限
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:40
     */
    public void updateAuth(PageData pd){
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        //处理数据权限
        String create_user = userParams.split(":")[1];
        String time = DateTimeUtil.getDateTimeStr();
        String[] role_ids = pd.get("role_ids").toString().split(",");
        //删除用户角色表。
        pd.put("user_id",pd.get("id"));
        pd.put("role_ids", Arrays.asList(role_ids));
        this.baseMapper.delUserRole(pd);
        if(Verify.verifyIsNotNull(pd.get("role_ids"))){
            String user_id = pd.get("id").toString();
            List<UserRole> list = new ArrayList<UserRole>();
            //执行保存
            for (int i = 0; i < role_ids.length; i++) {
                UserRole userRole = new UserRole();
                //主键id
                userRole.setId(GuidUtil.getUuid());
                userRole.setRole_id(role_ids[i]);
                userRole.setUser_id(user_id);
                userRole.setCreate_time(time);
                userRole.setCreate_user(create_user);
                list.add(userRole);
            }
            userRoleService.saveBatch(list);
        }

        //处理数据权限
        UserOrganize userOrganize = new UserOrganize();
        String[] showAuthData = pd.get("showAuthData").toString().split(",");
        String[] operaAuthData = pd.get("operaAuthData").toString().split(",");
        StringBuilder authOrgIds = new StringBuilder();
        StringBuilder authOrgCascade = new StringBuilder();
        StringBuilder authParams = new StringBuilder();
        if(Verify.verifyIsNotNull(pd.get("showAuthData"))){
            for (int i = 0; i < showAuthData.length; i++) {
                String showAuth[] = showAuthData[i].toString().split(":");
                authOrgIds.append(showAuth[0]).append(",");
                authOrgCascade.append(showAuth[1]).append(",");
                if(ArrayUtils.contains(operaAuthData,showAuthData[i])){
                    authParams.append(showAuth[0]).append(":Y").append(",");
                }else{
                    authParams.append(showAuth[0]).append(":N").append(",");
                }
            }
            if(authOrgIds.length()>0){
                userOrganize.setAuthOrgIds(authOrgIds.substring(0,authOrgIds.length()-1));
                userOrganize.setAuthOrgCascade(authOrgCascade.substring(0,authOrgCascade.length()-1));
                userOrganize.setAuthParams(authParams.substring(0,authParams.length()-1));
            }
        }
        userOrganize.setUpdate_time(time);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",pd.get("user_id").toString());
        queryWrapper.eq("organize_id",pd.get("organize_id").toString());
        userOrganizeService.update(userOrganize,queryWrapper);
    }

    /**
     * @title updateUserOrgUseStatus
     * @description 更新用户组织状态
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:40
     */
    public void updateUserOrgUseStatus(PageData pd){
        this.baseMapper.updateUserOrgUseStatus(pd);
    }


}