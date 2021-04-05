<#assign isContainFile = 'false'>
<#list fieldList as obj>
    <#if obj.field_operat == 'Y'>
        <#if obj.show_type == '8'>
            <#assign isContainFile = 'true'>
        </#if>
    </#if>
</#list>
package ${tablePd.pack_path}.${tablePd.mod_name}.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.qingfeng.base.controller.BaseController;
import com.qingfeng.entity.MyResponse;
import com.qingfeng.entity.QueryRequest;
<#if isContainFile == 'true'>
import com.qingfeng.common.service.IUploadService;
import com.qingfeng.entity.common.UploadFile;
</#if>
import ${tablePd.pack_path}.entity.${tablePd.mod_name}.${tablePd.bus_name?cap_first};
import com.qingfeng.entity.system.UserOrganize;
import com.qingfeng.exception.MyException;
import ${tablePd.pack_path}.${tablePd.mod_name}.service.I${tablePd.bus_name?cap_first}Service;
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qingfeng.utils.upload.ParaUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.*;


/**
* @title: ${tablePd.bus_name?cap_first}Controller
* @projectName: ${tablePd.bus_name?cap_first}Controller
* @description: TODO
* @author: qingfeng
* @createTime 2021/4/3 0003 20:30
*/
@Slf4j
@Validated
@RestController
@RequestMapping("${tablePd.bus_name}")
public class ${tablePd.bus_name?cap_first}Controller extends BaseController {

    @Autowired
    private I${tablePd.bus_name?cap_first}Service ${tablePd.bus_name}Service;
    @Autowired
    private IUserOrganizeService userOrganizeService;
    @Autowired
    private IUserService userService;
<#if isContainFile == 'true'>
    @Autowired
    private IUploadService uploadService;
</#if>

    /**
    * @title findListPage
    * @description 查询数据列表
    * @author qingfeng
    * @updateTime 2021/4/3 0003 20:30
    */
    @GetMapping("/findListPage")
    @PreAuthorize("hasAnyAuthority('${tablePd.bus_name}:info')")
    public MyResponse findListPage(QueryRequest queryRequest, ${tablePd.bus_name?cap_first} ${tablePd.bus_name}) {
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
        ${tablePd.bus_name}.setAuth_user(user_id);
        ${tablePd.bus_name}.setAuth_organize_ids(auth_organize_ids);
        IPage<Mycontent> list = ${tablePd.bus_name}Service.findListPage(${tablePd.bus_name}, queryRequest);
        <#if isContainFile == 'true'>
        for (${tablePd.bus_name?cap_first} ${tablePd.bus_name}Obj:list.getRecords()) {
            <#list fieldList as obj>
            <#if obj.field_operat == 'Y'>
            <#if obj.show_type == '8'>
            //查询${obj.field_comment}附件信息
            Collection<UploadFile> ${obj.field_name}FileList = uploadService.listByIds(Arrays.asList(${tablePd.bus_name}Obj.get${obj.field_name?cap_first}().split(",")));
            ${tablePd.bus_name}Obj.setIntroFileList(${obj.field_name}FileList);
            </#if>
            </#if>
            </#list>
        }
        </#if>
        Map<String, Object> dataTable = MyUtil.getDataTable(list);
        return new MyResponse().data(dataTable);
    }

    /**
    * @title findList
    * @description 查询列表
    * @author qingfeng
    * @updateTime 2021/4/3 0003 20:31
    */
    @GetMapping("/findList")
    public MyResponse findList(QueryRequest queryRequest, ${tablePd.bus_name?cap_first} ${tablePd.bus_name}) {
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
        ${tablePd.bus_name}.setAuth_user(user_id);
        ${tablePd.bus_name}.setAuth_organize_ids(auth_organize_ids);
        List<${tablePd.bus_name?cap_first}> ${tablePd.bus_name}List = ${tablePd.bus_name}Service.findList(${tablePd.bus_name});
        <#if isContainFile == 'true'>
        for (${tablePd.bus_name?cap_first} ${tablePd.bus_name}Obj:${tablePd.bus_name}List) {
            <#list fieldList as obj>
            <#if obj.field_operat == 'Y'>
            <#if obj.show_type == '8'>
            //查询${obj.field_comment}附件信息
            Collection<UploadFile> ${obj.field_name}FileList = uploadService.listByIds(Arrays.asList(${tablePd.bus_name}Obj.get${obj.field_name?cap_first}().split(",")));
            ${tablePd.bus_name}Obj.setIntroFileList(${obj.field_name}FileList);
            </#if>
            </#if>
            </#list>
        }
        </#if>
        return new MyResponse().data(${tablePd.bus_name}List);
    }

    /**
    * @title save
    * @description 保存方法
    * @author qingfeng
    * @updateTime 2021/4/3 0003 20:31
    */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('${tablePd.bus_name}:add')")
    public void save(@Valid @RequestBody ${tablePd.bus_name?cap_first} ${tablePd.bus_name}) throws MyException {
        try {
            this.${tablePd.bus_name}Service.save${tablePd.bus_name?cap_first}(${tablePd.bus_name});
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
    * @updateTime 2021/4/3 0003 20:31
    */
    @PutMapping
    @PreAuthorize("hasAnyAuthority('${tablePd.bus_name}:edit')")
    public void update(@Valid @RequestBody ${tablePd.bus_name?cap_first} ${tablePd.bus_name}) throws MyException {
        try {
            this.${tablePd.bus_name}Service.update${tablePd.bus_name?cap_first}(${tablePd.bus_name});
        } catch (Exception e) {
            String message = "修改信息失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
    * @title delete
    * @description 删除方法
    * @author qingfeng
    * @updateTime 2021/4/3 0003 20:31
    */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAnyAuthority('${tablePd.bus_name}:del')")
    public void delete(@NotBlank(message = "{required}") @PathVariable String ids) throws MyException {
        try {
            String[] del_ids = ids.split(StringPool.COMMA);
            <#if isContainFile == 'true'>
            //删除主表附件信息
            for (String id:del_ids) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("obj_id",id);
                List<UploadFile> fileList = uploadService.list(queryWrapper);
                for (UploadFile fPd:fileList) {
                    //查询信息
                    File pathFile = new File(ParaUtil.localName+fPd.getFile_path());
                    pathFile.delete();
                    pathFile.deleteOnExit();
                    uploadService.removeById(fPd.getId());
                }
            }
            </#if>
            this.${tablePd.bus_name}Service.removeByIds(Arrays.asList(del_ids));
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
    * @updateTime 2021/4/3 0003 20:31
    */
    @PostMapping("/updateStatus")
    @PreAuthorize("hasAnyAuthority('${tablePd.bus_name}:status')")
    public void updateStatus(@Valid @RequestBody ${tablePd.bus_name?cap_first} ${tablePd.bus_name}) throws MyException {
        try {
            this.${tablePd.bus_name}Service.updateById(${tablePd.bus_name});
        } catch (Exception e) {
            String message = "状态修改失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

}
