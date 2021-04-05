package com.qingfeng.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @ProjectName AuthUser
 * @author Administrator
 * @version 1.0.0
 * @Description 权限用户信息
 * @createTime 2021/4/3 0003 19:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends User {
    private static final long serialVersionUID = -1748289340320186418L;
    private String id;
    private String type;
    private String login_name;
    private String login_password;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String birth_date;
    private String live_address;
    private String birth_address;
    private String head_address;
    private String motto;
    private String status;
    private String order_by;
    private String open_id;
    private String theme_id;
    private String init_password;
    private String pwd_error_num;
    private String pwd_error_time;
    private String remark;
    private String create_time;
    private String create_user;
    private String last_login_time;
    private String browser;
    private String os;
    private String ipaddr;
    private String iprealaddr;
    private String depart_leader;
    private String direct_leader;
    private String branch_leader;

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
