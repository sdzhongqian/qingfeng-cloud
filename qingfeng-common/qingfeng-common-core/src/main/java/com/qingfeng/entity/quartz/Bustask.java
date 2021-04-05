package com.qingfeng.entity.quartz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName Bustask
 * @author Administrator
 * @version 1.0.0
 * @Description 定时器
 * @createTime 2021/4/3 0003 19:20
 */
@Data
@TableName("quartz_bustask")
public class Bustask implements Serializable {

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
     * 任务名称
     */
    @TableField("job_name")
    private String job_name;

    /**
     * 任务分组
     */
    @TableField("job_group")
    private String job_group;

    /**
     * 任务描述
     */
    @TableField("description")
    private String description;
    /**
     * 通知人
     */
    @TableField("notice_user")
    private String notice_user;

    /**
     * 执行类
     */
    @TableField("job_class_name")
    private String job_class_name;
    /**
     * 执行表达式
     */
    @TableField("cron_expression")
    private String cron_expression;
    /**
     * 执行时间
     */
    @TableField("trigger_time")
    private String trigger_time;
    /**
     * 执行状态
     */
    @TableField("trigger_state")
    private String trigger_state;
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
    private String oldJobName;

    @TableField(exist = false)
    private String oldJobGroup;
}