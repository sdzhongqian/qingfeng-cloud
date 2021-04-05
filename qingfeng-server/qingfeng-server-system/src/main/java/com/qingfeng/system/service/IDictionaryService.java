package com.qingfeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Dictionary;
import com.qingfeng.entity.system.Role;

import java.util.List;

/**
 * @ProjectName IDictionaryService
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:26
 */
public interface IDictionaryService extends IService<Dictionary> {

    //查询数据分页列表
    IPage<Dictionary> findListPage(Dictionary dictionary, QueryRequest request);

    //查询数据列表
    List<Dictionary> findList(Dictionary dictionary);

}