package com.qingfeng.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.qingfeng.base.controller.BaseController;
import com.qingfeng.entity.MyResponse;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.*;
import com.qingfeng.exception.MyException;
import com.qingfeng.system.service.*;
import com.qingfeng.utils.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ProjectName RoleController
 * @author qingfeng
 * @version 1.0.0
 * @Description 角色信息
 * @createTime 2021/4/3 0003 21:08
 */
@Slf4j
@Validated
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrganizeService organizeService;
    @Autowired
    private IUserOrganizeService userOrganizeService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleMenuService roleMenuService;


    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:08
     */
    @GetMapping("/findListPage")
    @PreAuthorize("hasAnyAuthority('role:info')")
    public MyResponse findListPage(QueryRequest queryRequest,Role role) {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        //处理数据权限
        String user_id = userParams.split(":")[1];
        UserOrganize uoParam = new UserOrganize();
        uoParam.setUser_id(user_id);
        UserOrganize userOrganize = userOrganizeService.findUserOrganizeInfo(uoParam);
        List<String> auth_organize_ids = new ArrayList<String>();
        if(Verify.verifyIsNotNull(userOrganize)){
            if(Verify.verifyIsNotNull(userOrganize.getAuthOrgIds())){
                auth_organize_ids = Arrays.asList(userOrganize.getAuthOrgIds().split(","));
            }
        }
        role.setAuth_user(user_id);
        role.setAuth_organize_ids(auth_organize_ids);
        Map<String, Object> dataTable = MyUtil.getDataTable(roleService.findListPage(role, queryRequest));
        return new MyResponse().data(dataTable);
    }

    /**
     * @title save
     * @description 保存方法
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:08
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('role:add')")
    public void save(@Valid @RequestBody Role role) throws MyException {
        try {
            // 创建用户
            String id = GuidUtil.getUuid();
            role.setId(id);
            String time = DateTimeUtil.getDateTimeStr();
            role.setCreate_time(time);
            role.setStatus("0");
            role.setType("1");
            //处理数据权限
            String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
            role.setCreate_user(authParams.split(":")[1]);
            role.setCreate_organize(authParams.split(":")[2]);
            this.roleService.save(role);
        } catch (Exception e) {
            String message = "新增信息失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title update
     * @description 更新方法
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:09
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public void update(@Valid @RequestBody Role role) throws MyException {
        try {
            // 更新组织信息
            String time = DateTimeUtil.getDateTimeStr();
            String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
            role.setUpdate_time(time);
            role.setUpdate_user(authParams.split(":")[1]);
            this.roleService.updateById(role);
        } catch (Exception e) {
            String message = "修改组织失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title delete
     * @description 删除方法
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:09
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAnyAuthority('role:del')")
    public void delete(@NotBlank(message = "{required}") @PathVariable String ids) throws MyException {
        try {
            String[] del_ids = ids.split(StringPool.COMMA);
            this.roleService.removeByIds(Arrays.asList(del_ids));
        } catch (Exception e) {
            String message = "删除失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title updateStatus
     * @description 更新状态
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:09
     */
    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyAuthority('role:status')")
    public void updateStatus(@Valid @RequestBody Role role) throws MyException {
        try {
            this.roleService.updateById(role);
        } catch (Exception e) {
            String message = "状态修改失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title updateAuth
     * @description 更新权限
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:09
     */
    @PostMapping("/updateAuth")
    public void updateAuth(@RequestBody PageData pd) throws MyException {
        try {
            String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
            String create_user = userParams.split(":")[1];

            String role_id = pd.get("role_id").toString();
            String time = DateTimeUtil.getDateTimeStr();
            String[] ids = pd.get("ids").toString().split(",");
            pd.put("menu_ids", Arrays.asList(ids));
            roleMenuService.delRoleMenu(pd);
            List<RoleMenu> list = new ArrayList<RoleMenu>();
            for (int i = 0; i < ids.length; i++) {
                RoleMenu roleMenu = new RoleMenu();
                //主键id
                roleMenu.setId(GuidUtil.getUuid());
                roleMenu.setMenu_id(ids[i]);
                roleMenu.setRole_id(role_id);
                roleMenu.setCreate_user(create_user);
                roleMenu.setCreate_time(time);
                list.add(roleMenu);
            }
            roleMenuService.saveBatch(list);
        } catch (Exception e) {
            String message = "更新权限信息失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title findRoleMenuList
     * @description 查询角色菜单信息
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:09
     */
    @PostMapping("/findRoleMenuList")
    public MyResponse findRoleMenuList(@RequestBody PageData pd) throws MyException {
        List<String> roleMenuList = roleMenuService.findRoleMenuList(pd);
        return new MyResponse().data(roleMenuList);
    }

    /**
     * @title exportData
     * @description 导出数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:10
     */
    @ApiOperation("导出组织信息接口")
    @RequestMapping(value = "/exportData", method = RequestMethod.GET)
    public void exportData(Role role,@RequestParam String authName,
                           HttpServletResponse response) throws Exception {
        PageData pd = new PageData();
        //处理数据权限
        String authNames[] = authName.split(":");
        pd.put("user_id",authNames[1]);
        PageData orgPd = userService.findUserOrganizeInfo(pd);
        if(Verify.verifyIsNotNull(orgPd)){
            if(Verify.verifyIsNotNull(orgPd.get("authOrgIds"))){
                role.setAuth_organize_ids(Arrays.asList(orgPd.get("authOrgIds").toString().split(",")));
            }else{
                role.setAuth_organize_ids(new ArrayList<String>());
            }
        }
        role.setAuth_user(authNames[1]);
        List<Role> list = roleService.findList(role);
        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put("obj", pd);
        beans.put("list", list);
        String tempPath = "";
        String toFile = "";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"templates";
        tempPath = path+"/excelExport/system_role.xls";
        toFile = path+"/excelExport/temporary/system_role.xls";
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(tempPath, beans, toFile);
        FileUtil.downFile(response, toFile, "青锋系统角色基础信息_" + DateTimeUtil.getDateTimeStr() + ".xls");
        File file = new File(toFile);
        file.delete();
        file.deleteOnExit();
    }

}
