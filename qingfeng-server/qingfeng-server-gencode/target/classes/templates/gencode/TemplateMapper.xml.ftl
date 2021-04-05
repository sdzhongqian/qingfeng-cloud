<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#assign statusType = ''>
<#assign isContainStatus = 'false'>
<#assign isContainType = 'false'>
<#list fieldList as obj>
    <#if obj.field_name == 'status' && (tablePd.status_type == '0' || tablePd.status_type == '1')>
        <#assign statusType = ' a.status+0 asc,'>
        <#assign isContainStatus = 'true'>
    </#if>
    <#if obj.field_name == 'type'>
        <#assign isContainType = 'true'>
    </#if>
</#list>
<mapper namespace="${tablePd.pack_path}.${tablePd.mod_name}.mapper.${tablePd.bus_name?cap_first}Mapper">
    <#assign ownOrderBy = 'false'>
    <#assign ownChildOrderBy = 'false'>

    <!--数据权限-->
    <sql id="authPageSql">
        <!-- 数据权限 -->
        <if test="(obj.auth_organize_ids == null or obj.auth_organize_ids.size == 0) and (obj.auth_user != null and obj.auth_user != '')">
            and a.create_user=${'#'}{obj.auth_user}
        </if>
        <if test="obj.auth_organize_ids != null and obj.auth_organize_ids.size > 0">
            and (a.create_user=${'#'}{obj.auth_user} or a.create_organize in
            <foreach collection="obj.auth_organize_ids" item="organize_id" open="(" separator="," close=")">
                ${'#'}{organize_id}
            </foreach>
            )
        </if>
    </sql>
    <sql id="authPdSql">
        <!-- 数据权限 -->
        <if test="(auth_organize_ids == null or auth_organize_ids.size == 0) and (auth_user != null and auth_user != '')">
            and a.create_user=${'#'}{auth_user}
        </if>
        <if test="auth_organize_ids != null and auth_organize_ids.size > 0">
            and (a.create_user=${'#'}{auth_user} or a.create_organize in
            <foreach collection="auth_organize_ids" item="organize_id" open="(" separator="," close=")">
                ${'#'}{organize_id}
            </foreach>
            )
        </if>
    </sql>
    
    <select id="findListPage" parameterType="${tablePd.bus_name?cap_first}" resultType="${tablePd.bus_name?cap_first}">
        select
        <#if tablePd.temp_type == '1'>
            a.${tablePd.tree_pid} as "${tablePd.tree_pid}",
            ifnull(b.num,0) as "child_num",
            p.name as "parent_name",
        </#if>
        <#list fieldList as obj>
            <#if obj_has_next>
            a.${obj.field_name} as "${obj.field_name}",
            </#if>
            <#if !obj_has_next>
            a.${obj.field_name} as "${obj.field_name}"
            </#if>
        </#list>
            from ${tablePd.table_name} a
            <#if tablePd.temp_type == '1'>
            left join ${tablePd.table_name} p on a.${tablePd.tree_pid}=p.id
            </#if>
            <#if tablePd.temp_type == '1'>
            left join (
                select
                count(*) as "num",
                a.${tablePd.tree_pid} as "${tablePd.tree_pid}"
                from ${tablePd.table_name} a
                group by a.${tablePd.tree_pid}
            ) b on a.id=b.${tablePd.tree_pid}
            </#if>
            where 1=1
            <#if isContainStatus == 'true'>
            <if test="obj.status != null and obj.status != ''">
            and a.status = ${'#'}{obj.status}
            </if>
            </#if>
            <#if isContainType == 'true'>
            <if test="obj.type != null and obj.type != ''">
            and a.type = ${'#'}{obj.type}
            </if>
            </#if>
            <#list fieldList as obj>
            <#if obj.field_query == 'Y'>
            <if test="obj.${obj.field_name} != null and obj.${obj.field_name} != ''">
            <#if obj.query_type == '=' ||obj.query_type == '>' ||obj.query_type == '>='||obj.query_type == '!='>
            and a.${obj.field_name} ${obj.query_type} ${'#'}{obj.${obj.field_name}}
            </#if>
            <#if obj.query_type == '<' ||obj.query_type == '<='>
            and a.${obj.field_name} <![CDATA[ ${obj.query_type} ]]> ${'#'}{obj.${obj.field_name}}
            </#if>
            <#if obj.query_type == 'like'>
            and a.${obj.field_name} ${obj.query_type} concat('%',concat(${'#'}{obj.${obj.field_name}},'%'))
            </#if>
            <#if obj.query_type == 'is null'||obj.query_type == 'is not null'>
            and a.${obj.field_name} ${obj.query_type}
            </#if>
            <#if obj.query_type == ''||obj.query_type == ''>
                and a.${obj.field_name} = ${'#'}{obj.${obj.field_name}}
            </#if>
            <#if obj.query_type == 'time_period'>
            <if test="obj.start_time != null and obj.start_time != ''">
            and a.${obj.field_name} >= ${'#'}{obj.start_time}
            </if>
            <if test="obj.end_time != null and obj.end_time != ''">
            and a.${obj.field_name} <![CDATA[ <= ]]> ${'#'}{obj.end_time}
            </if>
            </#if>
            </if>
            </#if>
            <#if obj.field_query == 'order_by'>
            <#assign ownOrderBy = 'true'>
            </#if>
            </#list>
            <#if tablePd.temp_type == '1'>
            <if test="obj.${tablePd.tree_pid} != null and obj.${tablePd.tree_pid} != ''">
            and a.${tablePd.tree_pid} = ${'#'}{obj.${tablePd.tree_pid}}
            </if>
            </#if>
            <include refid="authPageSql"></include>
            <#if ownOrderBy == 'true'>
            order by ${statusType} a.order_by+0 asc , a.create_time desc
            </#if>
            <#if ownOrderBy == 'false'>
            order by ${statusType} a.create_time desc
            </#if>
    </select>


    <!--查询列表-->
    <select id="findList" parameterType="${tablePd.bus_name?cap_first}" resultType="${tablePd.bus_name?cap_first}">
        select
        <#if tablePd.temp_type == '1'>
            a.${tablePd.tree_pid} as "${tablePd.tree_pid}",
            ifnull(b.num,0) as "child_num",
        </#if>
        <#list fieldList as obj>
        <#if obj_has_next>
            a.${obj.field_name} as "${obj.field_name}",
        </#if>
        <#if !obj_has_next>
            a.${obj.field_name} as "${obj.field_name}"
        </#if>
        </#list>
            from ${tablePd.table_name} a
            <#if tablePd.temp_type == '1'>
            left join (
                select
                count(*) as "num",
                a.${tablePd.tree_pid} as "${tablePd.tree_pid}"
                from ${tablePd.table_name} a
                group by a.${tablePd.tree_pid}
            ) b on a.id=b.${tablePd.tree_pid}
            </#if>
            where 1=1
            <#if isContainStatus == 'true'>
            <if test="status != null and status != ''">
            and a.status = ${'#'}{status}
            </if>
            </#if>
            <#if isContainType == 'true'>
            <if test="type != null and type != ''">
            and a.type = ${'#'}{type}
            </if>
            </#if>
            <#list fieldList as obj>
            <#if obj.field_query == 'Y'>
            <if test="${obj.field_name} != null and ${obj.field_name} != ''">
            <#if obj.query_type == '=' ||obj.query_type == '>' ||obj.query_type == '>='||obj.query_type == '!='>
            and a.${obj.field_name} ${obj.query_type} ${'#'}{${obj.field_name}}
            </#if>
            <#if obj.query_type == '<' ||obj.query_type == '<='>
            and a.${obj.field_name} <![CDATA[ ${obj.query_type} ]]> ${'#'}{${obj.field_name}}
            </#if>
            <#if obj.query_type == 'like'>
            and a.${obj.field_name} ${obj.query_type} concat('%',concat(${'#'}{${obj.field_name}},'%'))
            </#if>
            <#if obj.query_type == 'is null'||obj.query_type == 'is not null'>
            and a.${obj.field_name} ${obj.query_type}
            </#if>
            <#if obj.query_type == ''||obj.query_type == ''>
                and a.${obj.field_name} = ${'#'}{${obj.field_name}}
            </#if>
            </if>
            </#if>
            <#if obj.field_query == 'order_by'>
                <#assign ownOrderBy = 'true'>
            </#if>
            </#list>
            <#if tablePd.temp_type == '1'>
            <if test="${tablePd.tree_pid} != null and ${tablePd.tree_pid} != ''">
            and a.${tablePd.tree_pid} = ${'#'}{${tablePd.tree_pid}}
            </if>
            </#if>
            <include refid="authPdSql"></include>
            <#if ownOrderBy == 'true'>
            order by ${statusType} a.order_by+0 asc , a.create_time desc
            </#if>
            <#if ownOrderBy == 'false'>
            order by ${statusType} a.create_time desc
            </#if>
    </select>


</mapper>