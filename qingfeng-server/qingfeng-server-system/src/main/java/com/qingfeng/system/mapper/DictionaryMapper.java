package com.qingfeng.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Dictionary;
import com.qingfeng.entity.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName DictionaryMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 21:22
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    //查询数据分页列表
    IPage<Dictionary> findListPage(Page page, @Param("obj") Dictionary dictionary);

    //查询数据列表
    List<Dictionary> findList(Dictionary dictionary);

}