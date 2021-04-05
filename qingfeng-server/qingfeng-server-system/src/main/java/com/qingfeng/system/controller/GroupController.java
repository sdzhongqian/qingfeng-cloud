package com.qingfeng.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.qingfeng.base.controller.BaseController;
import com.qingfeng.entity.MyResponse;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Group;
import com.qingfeng.entity.system.UserOrganize;
import com.qingfeng.exception.MyException;
import com.qingfeng.system.service.IGroupService;
import com.qingfeng.system.service.IUserOrganizeService;
import com.qingfeng.system.service.IUserService;
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
import java.util.*;

/**
 * @ProjectName GroupController
 * @author qingfeng
 * @version 1.0.0
 * @Description 用户组信息
 * @createTime 2021/4/3 0003 21:04
 */
@Slf4j
@Validated
@RestController
@RequestMapping("group")
public class GroupController extends BaseController {

    @Autowired
    private IGroupService groupService;
    @Autowired
    private IUserOrganizeService userOrganizeService;
    @Autowired
    private IUserService userService;

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:04
     */
    @GetMapping("/findListPage")
    @PreAuthorize("hasAnyAuthority('group:info')")
    public MyResponse findListPage(QueryRequest queryRequest, Group group) {
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
        group.setAuth_user(user_id);
        group.setAuth_organize_ids(auth_organize_ids);
        Map<String, Object> dataTable = MyUtil.getDataTable(groupService.findListPage(group, queryRequest));
        return new MyResponse().data(dataTable);
    }

    /**
     * @title 保存数据
     * @description TODO
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:04
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('group:add')")
    public void save(@Valid @RequestBody Group group) throws MyException {
        try {
            this.groupService.saveGroup(group);
        } catch (Exception e) {
            String message = "新增信息失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title update
     * @description 更新数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:04
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('group:edit')")
    public void update(@Valid @RequestBody Group group) throws MyException {
        try {
            this.groupService.updateGroup(group);
        } catch (Exception e) {
            String message = "修改信息失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title delete
     * @description 删除数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:04
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAnyAuthority('group:del')")
    public void delete(@NotBlank(message = "{required}") @PathVariable String ids) throws MyException {
        try {
            String[] del_ids = ids.split(StringPool.COMMA);
            this.groupService.removeByIds(Arrays.asList(del_ids));
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
     * @updateTime 2021/4/3 0003 21:04
     */
    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyAuthority('group:status')")
    public void updateStatus(@Valid @RequestBody Group group) throws MyException {
        try {
            this.groupService.updateById(group);
        } catch (Exception e) {
            String message = "状态修改失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title exportData
     * @description 导出数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:04
     */
    @ApiOperation("导出组织信息接口")
    @RequestMapping(value = "/exportData", method = RequestMethod.GET)
    public void exportData(Group group,@RequestParam String authName,
                           HttpServletResponse response) throws Exception {
        PageData pd = new PageData();
        //处理数据权限
        String authNames[] = authName.split(":");
        pd.put("user_id",authNames[1]);
        PageData orgPd = userService.findUserOrganizeInfo(pd);
        if(Verify.verifyIsNotNull(orgPd)){
            if(Verify.verifyIsNotNull(orgPd.get("authOrgIds"))){
                group.setAuth_organize_ids(Arrays.asList(orgPd.get("authOrgIds").toString().split(",")));
            }else{
                group.setAuth_organize_ids(new ArrayList<String>());
            }
        }
        group.setAuth_user(authNames[1]);
        List<Group> list = groupService.findList(group);
        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put("obj", pd);
        beans.put("list", list);
        String tempPath = "";
        String toFile = "";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"templates";
        tempPath = path+"/excelExport/system_group.xls";
        toFile = path+"/excelExport/temporary/system_group.xls";
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(tempPath, beans, toFile);
        FileUtil.downFile(response, toFile, "青锋系统用户组基础信息_" + DateTimeUtil.getDateTimeStr() + ".xls");
        File file = new File(toFile);
        file.delete();
        file.deleteOnExit();
    }

}
