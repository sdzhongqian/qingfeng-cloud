package com.qingfeng.entity.gencode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName Mytree
 * @author Administrator
 * @version 1.0.0
 * @Description 案例-代码生成器树表
 * @createTime 2021/4/3 0003 19:20
 */
@Data
@TableName("gencode_mytree")
public class Mytree implements Serializable {

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
     * 字典名称
     */
    @TableField("name")
    private String name;
    /**
     * 字典简称
     */
    @TableField("short_name")
    private String short_name;
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

    /**
    * 父级id
    */
    @TableField("parent_id")
    private String parent_id;

    @TableField(exist = false)
    private String parent_name;

    @TableField(exist = false)
    private List<String> auth_organize_ids;

    @TableField(exist = false)
    private String auth_user;

    @TableField(exist = false)
    private String start_time;

    @TableField(exist = false)
    private String end_time;

}