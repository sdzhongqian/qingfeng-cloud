package com.qingfeng.entity.gencode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName Gentable
 * @author Administrator
 * @version 1.0.0
 * @Description 代码生成器-数据表
 * @createTime 2021/4/3 0003 19:20
 */
@Data
@TableName("system_gentable")
public class Gentable implements Serializable {

    private static final long serialVersionUID = -4352868070794165001L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 表名称
     */
    @TableField("table_name")
    private String table_name;
    /**
     * 表描述
     */
    @TableField("table_comment")
    private String table_comment;
    /**
     * 模板类型
     */
    @TableField("temp_type")
    private String temp_type;
    /**
     * 生成包路径
     */
    @TableField("pack_path")
    private String pack_path;
    /**
     * 生成模块名
     */
    @TableField("mod_name")
    private String mod_name;
    /**
     * 生成业务名
     */
    @TableField("bus_name")
    private String bus_name;
    /**
     * 功能名称
     */
    @TableField("menu_name")
    private String menu_name;
    /**
     * 上级菜单id
     */
    @TableField("menu_id")
    private String menu_id;
    /**
     * 生成方式
     */
    @TableField("gen_type")
    private String gen_type;
    /**
     * 生成路径
     */
    @TableField("gen_path")
    private String gen_path;
    /**
     * 状态类型
     */
    @TableField("status_type")
    private String status_type;
    /**
     * 树表id
     */
    @TableField("tree_id")
    private String tree_id;
    /**
     * 树表父级id
     */
    @TableField("tree_pid")
    private String tree_pid;
    /**
     * 树表名称
     */
    @TableField("tree_name")
    private String tree_name;
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
    private List<Genfield> fieldList;

    @TableField(exist = false)
    private String excludeField;

    @TableField(exist = false)
    private String menu_pname;

    @TableField(exist = false)
    private String start_time;

    @TableField(exist = false)
    private String end_time;

}