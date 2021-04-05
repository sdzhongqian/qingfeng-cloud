package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.system.Area;
import com.qingfeng.entity.system.OrganizeRole;
import com.qingfeng.system.mapper.AreaMapper;
import com.qingfeng.system.mapper.OrganizeRoleMapper;
import com.qingfeng.system.service.IAreaService;
import com.qingfeng.system.service.IOrganizeRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName OrganizeRoleServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:37
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrganizeRoleServiceImpl extends ServiceImpl<OrganizeRoleMapper, OrganizeRole> implements IOrganizeRoleService {


}