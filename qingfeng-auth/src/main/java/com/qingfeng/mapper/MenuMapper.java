package com.qingfeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingfeng.utils.PageData;

import java.util.List;

/**
 * @ProjectName MenuMapper
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:11
 */
public interface MenuMapper extends BaseMapper<PageData> {

    //查询用户权限集合
    List<PageData> findUserPermissions(PageData pd);
}
