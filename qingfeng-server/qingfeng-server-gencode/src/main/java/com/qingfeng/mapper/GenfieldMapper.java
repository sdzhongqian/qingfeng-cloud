package com.qingfeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingfeng.entity.gencode.Genfield;
import com.qingfeng.utils.PageData;

import java.util.List;

/**
 * @ProjectName GenfieldMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description GenfieldMapper
 * @createTime 2021/4/3 0003 19:59
 */
public interface GenfieldMapper extends BaseMapper<Genfield> {

    //查询字段列表
    List<Genfield> findFieldList(PageData pd);

}