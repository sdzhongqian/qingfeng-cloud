package ${tablePd.pack_path}.entity.${tablePd.mod_name};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qingfeng.entity.common.UploadFile;
import lombok.Data;
import java.util.Collection;
import java.io.Serializable;
import java.util.List;

/**
* @title: ${tablePd.bus_name?cap_first}
* @projectName: ${tablePd.bus_name?cap_first}
* @description: TODO
* @author: qingfeng
* @date: 2021/3/8 0008 20:15
*/
@Data
@TableName("${tablePd.table_name}")
public class ${tablePd.bus_name?cap_first} implements Serializable {

    private static final long serialVersionUID = -4352868070794165001L;

<#list fieldList as obj>
<#if obj.field_name == 'id'>
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
</#if>
<#if obj.field_name != 'id'>
    /**
     * ${obj.field_comment}
     */
    @TableField("${obj.field_name}")
    private String ${obj.field_name};
</#if>
<#if obj.show_type == '8'>
    @TableField(exist = false)
    private Collection<UploadFile> ${obj.field_name}FileList;
</#if>
</#list>

<#if tablePd.temp_type == '1'>
    /**
    * 父级id
    */
    @TableField("parent_id")
    private String parent_id;

    @TableField(exist = false)
    private String parent_name;
</#if>

    @TableField(exist = false)
    private List<String> auth_organize_ids;

    @TableField(exist = false)
    private String auth_user;

    @TableField(exist = false)
    private String start_time;

    @TableField(exist = false)
    private String end_time;

}