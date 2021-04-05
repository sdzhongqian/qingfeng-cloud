package com.qingfeng.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName Area
 * @author Administrator
 * @version 1.0.0
 * @Description 地区信息
 * @createTime 2021/4/3 0003 19:22
 */
@Data
@TableName("system_area")
public class Area implements Serializable {

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
    @TableField("area_cascade")
    private String area_cascade;

    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 简称
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
     * 英文名称
     */
    @TableField("en_name")
    private String en_name;
    /**
     * 英文简称
     */
    @TableField("en_short_name")
    private String en_short_name;
    /**
     * 经度
     */
    @TableField("lng")
    private String lng;
    /**
     * 维度
     */
    @TableField("lat")
    private String lat;
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
    private String parent_name;

    @TableField(exist = false)
    private String child_num;

}