package com.qingfeng.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName Organize
 * @author Administrator
 * @version 1.0.0
 * @Description 组织信息
 * @createTime 2021/4/3 0003 19:23
 */
@Data
@TableName("system_organize")
public class Organize implements Serializable {

    private static final long serialVersionUID = -4352868070794165001L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 级联字段
     */
    @TableField("org_cascade")
    private String org_cascade;
    /**
     * 组织名称
     */
    @TableField("name")
    private String name;
    /**
     * 组织简称
     */
    @TableField("short_name")
    private String short_name;
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
     * 组织编码
     */
    @TableField("code")
    private String code;

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
    /**
     * 部门领导
     */
    @TableField("depart_leader")
    private String depart_leader;
    /**
     * 上级领导
     */
    @TableField("direct_leader")
    private String direct_leader;
    /**
     * 分管领导
     */
    @TableField("branch_leader")
    private String branch_leader;

    @TableField(exist = false)
    private List<String> auth_organize_ids;

    @TableField(exist = false)
    private String auth_user;

    @TableField(exist = false)
    private String parent_name;

    @TableField(exist = false)
    private String child_num;

    @TableField(exist = false)
    private String parent_cascade;



}