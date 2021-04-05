package com.qingfeng.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName OrganizeRole
 * @author Administrator
 * @version 1.0.0
 * @Description 组织角色信息
 * @createTime 2021/4/3 0003 19:24
 */
@Data
@TableName("system_organize_role")
public class OrganizeRole implements Serializable {

    private static final long serialVersionUID = -3166012934498268403L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     * 组织id
     */
    @TableField(value = "organize_id")
    private String organize_id;
    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private String role_id;
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