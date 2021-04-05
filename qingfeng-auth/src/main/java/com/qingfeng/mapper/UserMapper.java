package com.qingfeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingfeng.utils.PageData;

/**
 * @ProjectName UserMapper
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:12
 */
public interface UserMapper extends BaseMapper<PageData> {

    //查询用户信息
    PageData findUserInfo(PageData pd);

    //查询用户组织信息
    PageData findUserOrganizeInfo(PageData pd);

}
