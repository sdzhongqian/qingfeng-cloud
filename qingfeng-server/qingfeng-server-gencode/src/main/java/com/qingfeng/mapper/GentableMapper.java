package com.qingfeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.entity.gencode.Gentable;
import com.qingfeng.utils.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName GentableMapper
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 20:00
 */
public interface GentableMapper extends BaseMapper<Gentable> {

    //查询数据表分页
    List<PageData> findTableListPage(PageData pd);

    //查询数据表数量
    Integer findTableListNum(PageData pd);

    //查询数据表列表
    List<PageData> findTableList(PageData pd);

    //查询字段
    List<PageData> findColumndList(PageData pd);

    //查询数据分页列表
    IPage<Gentable> findListPage(Page page, @Param("obj") Gentable gentable);

    //查询菜单信息详情
    PageData findMenuInfo(PageData pd);


}