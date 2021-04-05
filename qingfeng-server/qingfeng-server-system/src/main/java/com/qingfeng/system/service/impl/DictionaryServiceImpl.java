package com.qingfeng.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Dictionary;
import com.qingfeng.entity.system.Role;
import com.qingfeng.system.mapper.DictionaryMapper;
import com.qingfeng.system.service.IDictionaryService;
import com.qingfeng.utils.Verify;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName DictionaryServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:32
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:32
     */
    public IPage<Dictionary> findListPage(Dictionary dictionary, QueryRequest request){
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, dictionary);
    }

    /**
     * @title findList
     * @description 查询数据列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:32
     */
    public List<Dictionary> findList(Dictionary dictionary){
        return this.baseMapper.findList(dictionary);
    }


}