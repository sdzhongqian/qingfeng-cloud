package com.qingfeng.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.qingfeng.base.controller.BaseController;
import com.qingfeng.entity.MyResponse;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Organize;
import com.qingfeng.entity.system.Role;
import com.qingfeng.entity.system.SystemUser;
import com.qingfeng.entity.system.UserOrganize;
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
 * @ProjectName OrganizeController
 * @author qingfeng
 * @version 1.0.0
 * @Description 组织信息
 * @createTime 2021/4/3 0003 21:06
 */
@Slf4j
@Validated
@RestController
@RequestMapping("organize")
public class OrganizeController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IOrganizeService organizeService;
    @Autowired
    private IUserOrganizeService userOrganizeService;
    @Autowired
    private IRoleService roleService;


    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:06
     */
    @GetMapping("/findListPage")
    @PreAuthorize("hasAnyAuthority('organize:info')")
    public MyResponse findListPage(QueryRequest queryRequest,Organize organize) {
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
        organize.setAuth_user(user_id);
        organize.setAuth_organize_ids(auth_organize_ids);
        Map<String, Object> dataTable = MyUtil.getDataTable(organizeService.findListPage(organize, queryRequest));
        return new MyResponse().data(dataTable);
    }

    /**
     * @title 保存方法
     * @description TODO
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:07 
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('organize:add')")
    public void save(@Valid @RequestBody Organize organize) throws MyException {
        try {
            System.out.println("----------进来了--------");
            System.out.println(JsonToMap.bean2json(organize));
            // 创建用户
            String id = GuidUtil.getUuid();
            organize.setId(id);
            String time = DateTimeUtil.getDateTimeStr();
            organize.setCreate_time(time);
            organize.setStatus("0");
            organize.setType("1");
            //处理数据权限
            String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
            organize.setCreate_user(authParams.split(":")[1]);
            organize.setCreate_organize(authParams.split(":")[2]);

            organize.setOrg_cascade(organize.getOrg_cascade()+id+"_");
            int level_num = Integer.parseInt(organize.getLevel_num())+1;
            organize.setLevel_num(level_num+"");
            this.organizeService.save(organize);
        } catch (Exception e) {
            String message = "新增组织失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title update
     * @description 更新方法
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:07
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('organize:edit')")
    public void update(@Valid @RequestBody Organize organize) throws MyException {
        try {
            // 更新组织信息
            String time = DateTimeUtil.getDateTimeStr();
            String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
            organize.setUpdate_time(time);
            organize.setUpdate_user(authParams.split(":")[1]);
            this.organizeService.updateById(organize);
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
     * @updateTime 2021/4/3 0003 21:07
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAnyAuthority('organize:del')")
    public void delete(@NotBlank(message = "{required}") @PathVariable String ids) throws MyException {
        try {
            String[] del_ids = ids.split(StringPool.COMMA);
            this.organizeService.removeByIds(Arrays.asList(del_ids));
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
     * @updateTime 2021/4/3 0003 21:07
     */
    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyAuthority('organize:status')")
    public void updateStatus(@Valid @RequestBody Organize organize) throws MyException {
        try {
            this.organizeService.updateById(organize);
        } catch (Exception e) {
            String message = "状态修改失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title findRoleAuth
     * @description 查询角色权限
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:07
     */
    @PostMapping("/findRoleAuth")
    public MyResponse findRoleAuth(@RequestBody PageData pd) throws MyException {
        pd.put("organize_id",pd.get("id"));
        List<Role> roleLs = roleService.findSimpleList(pd);
        List<Role> myRoleLs = organizeService.findOrganizeRoleList(pd);
        pd.put("roleLs",roleLs);
        pd.put("myRoleLs",myRoleLs);
        return new MyResponse().data(pd);
    }

    /**
     * @title updateAuth
     * @description 更新权限
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:07
     */
    @PostMapping("/updateAuth")
    public void updateAuth(@RequestBody PageData pd) throws MyException {
        try {
            organizeService.updateAuth(pd);
        } catch (Exception e) {
            String message = "更新权限信息失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title getTreeList
     * @description 查询树形列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:07 
     */
    @GetMapping("/getTreeList")
    public MyResponse getTreeList(Organize organize) throws IOException  {
        //处理数据权限
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        String user_id = userParams.split(":")[1];
//        String organize_id = userParams.split(":")[2];
        PageData pd = new PageData();
        pd.put("user_id",user_id);
        PageData orgPd = userService.findUserOrganizeInfo(pd);
        List<String> auth_organize_ids = new ArrayList<String>();
        if(Verify.verifyIsNotNull(orgPd)){
            if(Verify.verifyIsNotNull(orgPd.get("authOrgIds"))){
                auth_organize_ids = Arrays.asList(orgPd.get("authOrgIds").toString().split(","));
            }
        }
        organize.setAuth_user(user_id);
        organize.setAuth_organize_ids(auth_organize_ids);
        //获取用户
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",user_id);
        SystemUser user = userService.getOne(queryWrapper);
        if(!user.getType().equals("0")){//管理员
            organize.setOrg_cascade(orgPd.get("org_cascade").toString());
        }
        List<Organize> list = organizeService.findList(organize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list",list);
        return new MyResponse().data(map);
    }

    /**
     * @title exportData
     * @description 导出数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:08
     */
    @ApiOperation("导出组织信息接口")
    @RequestMapping(value = "/exportData", method = RequestMethod.GET)
    public void exportData(Organize organize,@RequestParam String authName,
                           HttpServletResponse response) throws Exception {
        PageData pd = new PageData();
        //处理数据权限
        String authNames[] = authName.split(":");
        pd.put("user_id",authNames[1]);
        PageData orgPd = userService.findUserOrganizeInfo(pd);
        if(Verify.verifyIsNotNull(orgPd)){
            if(Verify.verifyIsNotNull(orgPd.get("authOrgIds"))){
                organize.setAuth_organize_ids(Arrays.asList(orgPd.get("authOrgIds").toString().split(",")));
            }else{
                organize.setAuth_organize_ids(new ArrayList<String>());
            }
        }
        organize.setAuth_user(authNames[1]);
        List<Organize> list = organizeService.findList(organize);
        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put("obj", pd);
        beans.put("list", list);
        String tempPath = "";
        String toFile = "";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"templates";
        tempPath = path+"/excelExport/system_organize.xls";
        toFile = path+"/excelExport/temporary/system_organize.xls";
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(tempPath, beans, toFile);
        FileUtil.downFile(response, toFile, "青锋系统组织基础信息_" + DateTimeUtil.getDateTimeStr() + ".xls");
        File file = new File(toFile);
        file.delete();
        file.deleteOnExit();
    }

}
