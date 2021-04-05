package com.qingfeng.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName Menu
 * @author Administrator
 * @version 1.0.0
 * @Description 菜单信息
 * @createTime 2021/4/3 0003 19:23
 */
@Data
@TableName("system_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 7187628714679791771L;


    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 请求路径
     */
    @TableField("path")
    private String path;
    /**
     * 重定向路径
     */
    @TableField("redirect")
    private String redirect;

    /**
     * 组件
     */
    @TableField("component")
    private String component;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * keepAlive
     */
    @TableField("keepAlive")
    private String keepAlive;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 权限标识
     */
    @TableField("permission")
    private String permission;

    /**
     * 菜单级联
     */
    @TableField("menu_cascade")
    private String menu_cascade;

    /**
     * 父级id
     */
    @TableField("parent_id")
    private String parent_id;
    /**
     * 等级
     */
    @TableField("level_num")
    private String level_num;


    /**
     * 状态
     */
    @TableField("status")
    private String status;
    /**
     * 排序
     */
    @TableField("order_by")
    private String order_by;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String create_time;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String create_user;
    /**
     * 创建组织
     */
    @TableField("create_organize")
    private String create_organize;

    /**
     * 修改人
     */
    @TableField("update_user")
    private String update_user;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private String update_time;


    @TableField(exist = false)
    private List<String> auth_organize_ids;

    @TableField(exist = false)
    private String auth_user;

    @TableField(exist = false)
    private String child_num;

}
