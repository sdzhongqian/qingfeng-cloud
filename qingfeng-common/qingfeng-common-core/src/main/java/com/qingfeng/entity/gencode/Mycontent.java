package com.qingfeng.entity.gencode;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qingfeng.entity.common.UploadFile;
import lombok.Data;
import java.util.Collection;
import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName Mycontent
 * @author Administrator
 * @version 1.0.0
 * @Description 案例-代码生成器单表
 * @createTime 2021/4/3 0003 19:20
 */
@Data
@TableName("gencode_mycontent")
public class Mycontent implements Serializable {

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
     * 标题
     */
    @TableField("title")
    private String title;
    /**
     * 简介
     */
    @TableField("intro")
    private String intro;
    @TableField(exist = false)
    private Collection<UploadFile> introFileList;
    /**
     * 内容
     */
    @TableField("content")
    private String content;
    /**
     * 阅读数量
     */
    @TableField("read_num")
    private String read_num;
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
    private String start_time;

    @TableField(exist = false)
    private String end_time;

}