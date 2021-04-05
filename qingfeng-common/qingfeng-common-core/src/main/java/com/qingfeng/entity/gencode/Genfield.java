package com.qingfeng.entity.gencode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName Genfield
 * @author Administrator
 * @version 1.0.0
 * @Description 代码生成器-字段
 * @createTime 2021/4/3 0003 19:19
 */
@Data
@TableName("system_genfield")
public class Genfield implements Serializable {

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
     * 主表id
     */
    @TableField("table_id")
    private String table_id;

    /**
     * 字段名称
     */
    @TableField("field_name")
    private String field_name;
    /**
     * 字段描述
     */
    @TableField("field_comment")
    private String field_comment;
    /**
     * 字段类型
     */
    @TableField("field_type")
    private String field_type;
    /**
     * 是否添加编辑
     */
    @TableField("field_operat")
    private String field_operat;
    /**
     * 是否列表展示
     */
    @TableField("field_list")
    private String field_list;
    /**
     * 是否查询展示
     */
    @TableField("field_query")
    private String field_query;
    /**
     * 查询方式
     */
    @TableField("query_type")
    private String query_type;
    /**
     * 校验规则
     */
    @TableField("verify_rule")
    private String verify_rule;
    /**
     * 显示类型
     */
    @TableField("show_type")
    private String show_type;
    /**
     * 选项内容
     */
    @TableField("option_content")
    private String option_content;
    /**
     * 默认值
     */
    @TableField("default_value")
    private String default_value;
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


}