package com.qingfeng.entity.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName UploadFile
 * @author Administrator
 * @version 1.0.0
 * @Description 附件上传
 * @createTime 2021/4/3 0003 19:19
 */
@Data
@TableName("common_uploadfile")
public class UploadFile implements Serializable {

    private static final long serialVersionUID = -4352868070794165001L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 描述
     */
    @TableField("desnames")
    private String desnames;
    /**
     * 附件路径
     */
    @TableField("file_path")
    private String file_path;
    /**
     * 附件类型
     */
    @TableField("file_type")
    private String file_type;
    /**
     * 附件大小
     */
    @TableField("file_size")
    private String file_size;
    /**
     * 关联对象id
     */
    @TableField("obj_id")
    private String obj_id;
    /**
     * 关联对象子表id
     */
    @TableField("child_obj_id")
    private String child_obj_id;
    /**
     * 附件前缀
     */
    @TableField("file_suffix")
    private String file_suffix;
    /**
     * 附件来源
     */
    @TableField("source")
    private String source;
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
     * 创建时间
     */
    @TableField("create_time")
    private String create_time;
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
    private String show_file_path;

}
