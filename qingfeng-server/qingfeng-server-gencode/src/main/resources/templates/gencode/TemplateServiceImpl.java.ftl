<#assign isContainStatus = 'false'>
<#assign isContainType = 'false'>
<#list fieldList as obj>
    <#if obj.field_name == 'status' && (tablePd.status_type == '0' || tablePd.status_type == '1')>
        <#assign isContainStatus = 'true'>
    </#if>
    <#if obj.field_name == 'type'>
        <#assign isContainType = 'true'>
    </#if>
    <#if obj.field_operat == 'Y'>
        <#if obj.show_type == '8'>
            <#assign isContainFile = 'true'>
        </#if>
    </#if>
</#list>
package ${tablePd.pack_path}.${tablePd.mod_name}.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
<#if isContainFile == 'true'>
import com.qingfeng.common.service.IUploadService;
import com.qingfeng.entity.common.UploadFile;
</#if>
import ${tablePd.pack_path}.entity.${tablePd.mod_name}.${tablePd.bus_name?cap_first};
import ${tablePd.pack_path}.${tablePd.mod_name}.mapper.${tablePd.bus_name?cap_first}Mapper;
import ${tablePd.pack_path}.${tablePd.mod_name}.service.I${tablePd.bus_name?cap_first}Service;
import com.qingfeng.utils.DateTimeUtil;
import com.qingfeng.utils.GuidUtil;
import com.qingfeng.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qingfeng
 * @title: ${tablePd.bus_name?cap_first}ServiceImpl
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ${tablePd.bus_name?cap_first}ServiceImpl extends ServiceImpl<${tablePd.bus_name?cap_first}Mapper, ${tablePd.bus_name?cap_first}> implements I${tablePd.bus_name?cap_first}Service {

<#if isContainFile == 'true'>
    @Autowired
    private IUploadService uploadService;
</#if>

    /**
    * @title findListPage
    * @description 查询数据分页列表
    * @author qingfeng
    * @updateTime 2021/4/3 0003 20:57
    */
    public IPage<${tablePd.bus_name?cap_first}> findListPage(${tablePd.bus_name?cap_first} ${tablePd.bus_name}, QueryRequest request){
        Page<${tablePd.bus_name?cap_first}> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, ${tablePd.bus_name});
    }

    /**
    * @title findListPage
    * @description 查询数据列表
    * @author qingfeng
    * @updateTime 2021/4/3 0003 20:58
    */
    public List<${tablePd.bus_name?cap_first}> findList(${tablePd.bus_name?cap_first} ${tablePd.bus_name}){
        return this.baseMapper.findList(${tablePd.bus_name});
    }

    /**
    * @title saveMycontent
    * @description 保存数据
    * @author qingfeng
    * @updateTime 2021/4/3 0003 20:58
    */
    public void save${tablePd.bus_name?cap_first}(${tablePd.bus_name?cap_first} ${tablePd.bus_name}){
        // 创建信息
        String id = GuidUtil.getUuid();
        ${tablePd.bus_name}.setId(id);
        String time = DateTimeUtil.getDateTimeStr();
        ${tablePd.bus_name}.setCreate_time(time);
    <#if isContainStatus == 'true'>
        ${tablePd.bus_name}.setType("1");
    </#if>
    <#if isContainType == 'true'>
        ${tablePd.bus_name}.setStatus("0");
    </#if>
        //处理数据权限
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        ${tablePd.bus_name}.setCreate_user(authParams.split(":")[1]);
        ${tablePd.bus_name}.setCreate_organize(authParams.split(":")[2]);
        this.save(${tablePd.bus_name});

<#list fieldList as obj>
    <#if obj.field_operat == 'Y'>
    <#if obj.show_type == '8'>
        //处理${obj.field_comment}附件信息-更新信息主要意义在于可删选删除垃圾图片
        UploadFile uploadFile = new UploadFile();
        uploadFile.setObj_id(id);
        uploadFile.setUpdate_user(authParams.split(":")[1]);
        uploadFile.setUpdate_time(time);
        String[] file_${obj.field_name}_ids = mycontent.get${obj.field_name?cap_first}().split(",");
        for (String file_${obj.field_name}_id:file_${obj.field_name}_ids) {
            uploadFile.setId(file_${obj.field_name}_id);
            uploadService.updateById(uploadFile);
        }
    </#if>
    </#if>
</#list>
    }

    /**
    * @title updateMycontent
    * @description 更新数据
    * @author qingfeng
    * @updateTime 2021/4/3 0003 20:58
    */
    public void update${tablePd.bus_name?cap_first}(${tablePd.bus_name?cap_first} ${tablePd.bus_name}){
        // 更新信息
        String id = ${tablePd.bus_name}.getId();
        String time = DateTimeUtil.getDateTimeStr();
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        ${tablePd.bus_name}.setUpdate_time(time);
        ${tablePd.bus_name}.setUpdate_user(authParams.split(":")[1]);
        this.updateById(${tablePd.bus_name});
<#list fieldList as obj>
    <#if obj.field_operat == 'Y'>
    <#if obj.show_type == '8'>
        //处理${obj.field_comment}附件信息-更新信息主要意义在于可删选删除垃圾图片
        UploadFile uploadFile = new UploadFile();
        uploadFile.setObj_id(id);
        uploadFile.setUpdate_user(authParams.split(":")[1]);
        uploadFile.setUpdate_time(time);
        String[] file_${obj.field_name}_ids = mycontent.get${obj.field_name?cap_first}().split(",");
        for (String file_${obj.field_name}_id:file_${obj.field_name}_ids) {
            uploadFile.setId(file_${obj.field_name}_id);
            uploadService.updateById(uploadFile);
        }
    </#if>
    </#if>
</#list>
    }


}