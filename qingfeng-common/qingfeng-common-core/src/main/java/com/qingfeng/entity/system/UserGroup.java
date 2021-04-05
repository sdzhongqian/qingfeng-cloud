package com.qingfeng.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName UserGroup
 * @author Administrator
 * @version 1.0.0
 * @Description 用户-用户组信息
 * @createTime 2021/4/3 0003 19:24
 */
@Data
@TableName("system_user_group")
public class UserGroup implements Serializable {


    private static final long serialVersionUID = -3166012934498268403L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String user_id;

    @TableField(value = "user_name")
    private String user_name;

    /**
     * 组id
     */
    @TableField(value = "group_id")
    private String group_id;
    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String create_user;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private String create_time;
    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private String update_time;

}