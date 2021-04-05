package com.qingfeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Group;

import java.util.List;

/**
 * @ProjectName IGroupService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:26
 */
public interface IGroupService extends IService<Group> {

    //查询数据分页列表
    IPage<Group> findListPage(Group group, QueryRequest request);

    //查询数据列表
    List<Group> findList(Group group);

    //保存信息
    void saveGroup(Group group);

    //更新信息
    void updateGroup(Group group);

}