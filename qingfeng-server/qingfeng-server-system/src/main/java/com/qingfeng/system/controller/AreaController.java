package com.qingfeng.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.qingfeng.base.controller.BaseController;
import com.qingfeng.entity.MyResponse;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.system.Area;
import com.qingfeng.entity.system.UserOrganize;
import com.qingfeng.exception.MyException;
import com.qingfeng.system.service.IAreaService;
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
import java.io.IOException;
import java.util.*;

/**
 * @ProjectName AreaController
 * @author qingfeng
 * @version 1.0.0
 * @Description 地区信息
 * @createTime 2021/4/3 0003 21:01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("area")
public class AreaController extends BaseController {

    @Autowired
    private IAreaService areaService;
    @Autowired
    private IUserOrganizeService userOrganizeService;
    @Autowired
    private IUserService userService;

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:01
     */
    @GetMapping("/findListPage")
    @PreAuthorize("hasAnyAuthority('area:info')")
    public MyResponse findListPage(QueryRequest queryRequest, Area area) {
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
        area.setAuth_user(user_id);
        area.setAuth_organize_ids(auth_organize_ids);
        Map<String, Object> dataTable = MyUtil.getDataTable(areaService.findListPage(area, queryRequest));
        return new MyResponse().data(dataTable);
    }

    /**
     * @title save
     * @description 保存数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:01
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('area:add')")
    public void save(@Valid @RequestBody Area area) throws MyException {
        try {
            // 创建用户
            String id = GuidUtil.getUuid();
            area.setId(id);
            String time = DateTimeUtil.getDateTimeStr();
            area.setCreate_time(time);
            area.setStatus("0");
            area.setType("1");
            //处理数据权限
            String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
            area.setCreate_user(authParams.split(":")[1]);
            area.setCreate_organize(authParams.split(":")[2]);

            area.setArea_cascade(area.getArea_cascade()+id+"_");
            int level_num = Integer.parseInt(area.getLevel_num())+1;
            area.setLevel_num(level_num+"");
            this.areaService.save(area);
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
     * @updateTime 2021/4/3 0003 21:01
     */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('area:edit')")
    public void update(@Valid @RequestBody Area area) throws MyException {
        try {
            // 更新组织信息
            String time = DateTimeUtil.getDateTimeStr();
            String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
            area.setUpdate_time(time);
            area.setUpdate_user(authParams.split(":")[1]);
            this.areaService.updateById(area);
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
     * @updateTime 2021/4/3 0003 21:02
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAnyAuthority('area:del')")
    public void delete(@NotBlank(message = "{required}") @PathVariable String ids) throws MyException {
        try {
            String[] del_ids = ids.split(StringPool.COMMA);
            this.areaService.removeByIds(Arrays.asList(del_ids));
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
     * @updateTime 2021/4/3 0003 21:02
     */
    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyAuthority('area:status')")
    public void updateStatus(@Valid @RequestBody Area area) throws MyException {
        try {
            this.areaService.updateById(area);
        } catch (Exception e) {
            String message = "状态修改失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title findList
     * @description 查询数据列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:02
     */
    @ApiOperation("查询数据列表接口")
    @GetMapping("/findList")
    public MyResponse findList(Area area) throws IOException  {
        //处理数据权限
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        String user_id = userParams.split(":")[1];
        PageData pd = new PageData();
        pd.put("user_id",user_id);
        PageData orgPd = userService.findUserOrganizeInfo(pd);
        List<String> auth_organize_ids = new ArrayList<String>();
        if(Verify.verifyIsNotNull(orgPd)){
            if(Verify.verifyIsNotNull(orgPd.get("authOrgIds"))){
                auth_organize_ids = Arrays.asList(orgPd.get("authOrgIds").toString().split(","));
            }
        }
        area.setAuth_user(user_id);
        area.setAuth_organize_ids(auth_organize_ids);
        List<Area> list = areaService.findList(area);
        return new MyResponse().data(list);
    }

    /**
     * @title exportData
     * @description 导出数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 21:02
     */
    @ApiOperation("导出组织信息接口")
    @RequestMapping(value = "/exportData", method = RequestMethod.GET)
    public void exportData(Area area,@RequestParam String authName,
                           HttpServletResponse response) throws Exception {
        PageData pd = new PageData();
        //处理数据权限
        String authNames[] = authName.split(":");
        pd.put("user_id",authNames[1]);
        PageData orgPd = userService.findUserOrganizeInfo(pd);
        if(Verify.verifyIsNotNull(orgPd)){
            if(Verify.verifyIsNotNull(orgPd.get("authOrgIds"))){
                area.setAuth_organize_ids(Arrays.asList(orgPd.get("authOrgIds").toString().split(",")));
            }else{
                area.setAuth_organize_ids(new ArrayList<String>());
            }
        }
        area.setAuth_user(authNames[1]);
        List<Area> list = areaService.findList(area);
        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put("obj", pd);
        beans.put("list", list);
        String tempPath = "";
        String toFile = "";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"templates";
        tempPath = path+"/excelExport/system_area.xls";
        toFile = path+"/excelExport/temporary/system_area.xls";
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(tempPath, beans, toFile);
        FileUtil.downFile(response, toFile, "青锋系统地区基础信息_" + DateTimeUtil.getDateTimeStr() + ".xls");
        File file = new File(toFile);
        file.delete();
        file.deleteOnExit();
    }

}
