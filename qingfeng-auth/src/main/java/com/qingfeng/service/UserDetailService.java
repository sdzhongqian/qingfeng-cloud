package com.qingfeng.service;

import com.qingfeng.entity.AuthUser;
import com.qingfeng.manager.UserManager;
import com.qingfeng.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ProjectName UserDetailService
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:13
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;

    /**
     * @title loadUserByUsername
     * @description 用户登录
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:13
     * @throws
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PageData pd = new PageData();
        if(username.contains(":")){
            pd.put("login_name",username.split(":")[0]);
        }else{
            pd.put("login_name",username);
        }
        PageData uPd = userManager.findUserInfo(pd);
        if(Verify.verifyIsNotNull(uPd)){
            if(uPd.getString("status").equals("2")){
                throw new UsernameNotFoundException("账号已休眠，请联系管理员");
            }else{
                if(uPd.getString("status").equals("0")){
                    //查询当前用户组织
                    pd.put("user_id",uPd.get("id"));
                    PageData orgPd = userManager.findUserOrganizeInfo(pd);
                    //登录成功
                    pd.put("user_id",uPd.get("id"));
                    String permissions = userManager.findUserPermissions(pd);
                    boolean notLocked = false;
                    if (StringUtils.equals("0", uPd.get("status").toString()))
                        notLocked = true;
                    AuthUser authUser = new AuthUser(uPd.get("login_name").toString()+":"+uPd.get("id").toString()+":"+orgPd.get("organize_id").toString(), uPd.get("login_password").toString(), true, true, true, notLocked,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
                    return transToAuthUser(authUser,uPd);

                }else if(uPd.getString("status").equals("N")){
                    throw new UsernameNotFoundException("账号已禁用，请联系管理员");
                }else if(uPd.getString("status").equals("2")){
                    throw new UsernameNotFoundException("账号已休眠，请联系管理员");
                }
            }
            return null;
        }else{
            throw new UsernameNotFoundException("登录名称不存在，请重新输入。");
        }
    }

    /**
     * @title transToAuthUser
     * @description 用户信息转换
     * @author Administrator
     * @updateTime 2021/4/3 0003 19:13
     * @throws
     */
    private AuthUser transToAuthUser(AuthUser authUser, PageData uPd) {
        authUser.setId(uPd.getString("id"));
        authUser.setType(uPd.getString("type"));
        authUser.setLogin_name(uPd.getString("login_name"));
        authUser.setLogin_password(uPd.getString("login_password"));
        authUser.setName(uPd.getString("name"));
        authUser.setSex(uPd.getString("sex"));
        authUser.setPhone(uPd.getString("phone"));
        authUser.setEmail(uPd.getString("email"));
        authUser.setBirth_date(uPd.getString("birth_date"));
        authUser.setLive_address(uPd.getString("live_address"));
        authUser.setBirth_address(uPd.getString("birth_address"));
        authUser.setHead_address(uPd.getString("head_address"));
        authUser.setMotto(uPd.getString("motto"));
        authUser.setStatus(uPd.getString("status"));
        authUser.setOrder_by(uPd.getString("order_by"));
        authUser.setLast_login_time(uPd.getString("last_login_time"));
        authUser.setBrowser(uPd.getString("browser"));
        authUser.setOs(uPd.getString("os"));
        authUser.setIpaddr(uPd.getString("ipaddr"));
        authUser.setIprealaddr(uPd.getString("iprealaddr"));
        authUser.setStatus(uPd.getString("status"));
        return authUser;
    }


    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTUyMDUyMjMsInVzZXJfbmFtZSI6ImFkbWluOjE6MzY3YmMyODRmMTNjNDMxNjkyMmM0OTRkOWJiMWZhYjkiLCJhdXRob3JpdGllcyI6WyJ1c2VyOnJlc2V0UHdkIiwiYnVzVGFzazpkZWwiLCJsb2dnZXI6ZXhwb3J0RXhjZWwiLCJtZW51OmRlbCIsImRpY3Rpb25hcnk6aW5mbyIsImFyZWE6aW5mbyIsInJvbGU6YWRkIiwidXNlck9ubGluZTpyZXRyZWF0IiwiYXJlYTphZGQiLCJidXNUYXNrOmVkaXQiLCJkaWN0aW9uYXJ5OnN0YXR1cyIsIm9yZ2FuaXplOnN0YXR1cyIsInJvbGU6ZGVsIiwibWVudTppbmZvIiwidGltVGFzazplZGl0IiwiZ2VuY29kZTp2aWV3Q29kZSIsImdyb3VwOmRlbCIsInJvbGU6YXNzaWduQXV0aCIsImdlbmNvZGU6ZWRpdCIsImFyZWE6ZWRpdCIsImdyb3VwOnN0YXR1cyIsIm9yZ2FuaXplOmFkZCIsImdyb3VwOmFkZCIsImdlbmNvZGU6aW1wb3J0IiwibWVudTplZGl0Iiwicm9sZTpzdGF0dXMiLCJncm91cDppbmZvIiwiYnVzVGFzazpleGVjdXRpb24iLCJteXRyZWU6aW5mbyIsImFyZWE6ZGVsIiwiYXJlYTpkb3dubG9hZCIsInRpbVRhc2s6ZXhlY3V0aW9uIiwibG9nZ2VyOmRlbCIsIm15dHJlZTphZGQiLCJ1c2VyOnN0YXR1cyIsImdyb3VwOmVkaXQiLCJidXNUYXNrOnN0b3BPclJlc3RvcmUiLCJyb2xlOmluZm8iLCJnZW5jb2RlOmluZm8iLCJvcmdhbml6ZTphc3NpZ25BdXRoIiwidXNlcjpkZWwiLCJtZW51OnN0YXR1cyIsImJ1c1Rhc2s6aW5mbyIsImdlbmNvZGU6ZGVsIiwidXNlcjppbmZvIiwibXl0cmVlOmVkaXQiLCJvcmdhbml6ZTpkZWwiLCJvcmdhbml6ZTpkb3dubG9hZCIsInVzZXI6cmVzZXRPcmdhbml6ZSIsIm15Y29udGVudDppbmZvIiwidXNlcjphc3NpZ25BdXRoIiwiYXJlYTpzdGF0dXMiLCJ0aW1UYXNrOmFkZCIsImdlbmNvZGU6Z2VuY29kZSIsIm15Y29udGVudDpkZWwiLCJvcmdhbml6ZTppbmZvIiwidXNlcjphZGQiLCJteXRyZWU6ZGVsIiwiZGljdGlvbmFyeTpkZWwiLCJ0aW1UYXNrOnN0b3BPclJlc3RvcmUiLCJsb2dnZXI6aW5mbyIsInVzZXI6ZG93bmxvYWQiLCJkaWN0aW9uYXJ5OmRvd25sb2FkIiwibWVudTphZGQiLCJ0aW1UYXNrOmRlbCIsInVzZXI6ZWRpdCIsImRpY3Rpb25hcnk6YWRkIiwiZGljdGlvbmFyeTplZGl0IiwibXljb250ZW50OmVkaXQiLCJ0aW1UYXNrOmluZm8iLCJteWNvbnRlbnQ6YWRkIiwib3JnYW5pemU6ZWRpdCIsInJvbGU6ZWRpdCIsImJ1c1Rhc2s6YWRkIiwicm9sZTpkb3dubG9hZCIsImdyb3VwOmRvd25sb2FkIl0sImp0aSI6ImIxMTcyYTNkLWI3MzAtNDMxMC05NDNlLTAxMTEyODUwOTk2YyIsImNsaWVudF9pZCI6InFpbmdmZW5nIiwic2NvcGUiOlsiYWxsIl19.iEvUVfqdlDgkap4tZZiD5b7bbVOQj-NdGnx6R-u9dVg";
        System.out.println(JwtHelper.decode(token));
        Map map=JwtHelper.headers(token);
        System.out.println(map);
//        Jwts.parser().setSigningKey(Constants.Jwt.KEY).parseClaimsJws(token).getBody().getSubject();
    }

}
