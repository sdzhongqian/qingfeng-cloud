package com.qingfeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.gencode.Genfield;
import com.qingfeng.mapper.GenfieldMapper;
import com.qingfeng.service.IGenfieldService;
import com.qingfeng.utils.PageData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @ProjectName GenfieldServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 20:01
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GenfieldServiceImpl extends ServiceImpl<GenfieldMapper, Genfield> implements IGenfieldService {

    /**
     * @title findFieldList
     * @description 查询字段列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:01
     */
    public List<Genfield> findFieldList(PageData pd){
        return this.baseMapper.findFieldList(pd);
    }

}