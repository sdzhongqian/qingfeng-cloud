package com.qingfeng.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qingfeng.annotation.validator.IsMobile;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName SystemUser
 * @author Administrator
 * @version 1.0.0
 * @Description 系统用户信息
 * @createTime 2021/4/3 0003 19:24
 */
@Data
@TableName("system_user")
public class SystemUser implements Serializable {

    private static final long serialVersionUID = -4352868070794165001L;

    /**
     * 用户 ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 用户名
     */
    @TableField("login_name")
    @Size(min = 4, max = 60, message = "{range}")
    private String login_name;

    /**
     * 密码
     */
    @TableField("login_password")
    private String login_password;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    @Size(max = 50, message = "{noMoreThan}")
    @Email(message = "{email}")
    private String email;

    /**
     * 联系电话
     */
    @TableField("phone")
    @IsMobile(message = "{mobile}")
    private String phone;

    /**
     * 出生日期
     */
    @TableField("birth_date")
    private String birth_date;
    /**
     * 居住地
     */
    @TableField("live_address")
    private String live_address;
    /**
     * 籍贯地址
     */
    @TableField("birth_address")
    private String birth_address;
    /**
     * 头像地址
     */
    @TableField("head_address")
    private String head_address;
    /**
     * 座右铭
     */
    @TableField("motto")
    private String motto;
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
     * 微信id
     */
    @TableField("open_id")
    private String open_id;
    /**
     * 当前主题
     */
    @TableField("theme_id")
    private String theme_id;
    /**
     * 是否初始密码
     */
    @TableField("init_password")
    private String init_password;

    /**
     * 密码输入错误次数
     */
    @TableField("pwd_error_num")
    private String pwd_error_num;
    /**
     * 密码输入错误时间
     */
    @TableField("pwd_error_time")
    private String pwd_error_time;
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
     * 最后登录时间
     */
    @TableField("last_login_time")
    private String last_login_time;
    /**
     * 登录浏览器
     */
    @TableField("browser")
    private String browser;
    /**
     * 登录系统
     */
    @TableField("os")
    private String os;
    /**
     * 登录ip
     */
    @TableField("ipaddr")
    private String ipaddr;
    /**
     * 登录地址
     */
    @TableField("iprealaddr")
    private String iprealaddr;

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
    private String organize_id;
    @TableField(exist = false)
    private String organize_name;
    //辅助添加
    @TableField(exist = false)
    private String old_login_name;
    @TableField(exist = false)
    private String fileIds;
    @TableField(exist = false)
    private String ids;

}
