<#list menuList as obj>
<#if obj.type == 'menu'>
-- 生成菜单
</#if>
<#if obj.type == 'button'>
<#if obj.code == 'add'>
-- 生成添加按钮
</#if>
<#if obj.code == 'edit'>
-- 生成编辑按钮
</#if>
<#if obj.code == 'del'>
-- 生成删除按钮
</#if>
<#if obj.code == 'info'>
-- 生成详情按钮
</#if>
</#if>
insert into system_menu (id,type,name,path,component,title,keepAlive,permission,parent_id,status,order_by,remark,create_time,create_user,create_organize)
	values
	('${obj.id}','${obj.type}','${obj.title}','${obj.path}','${obj.component}','${obj.title}','${obj.keepAlive}','${obj.permission}','${obj.parent_id}','${obj.status}','${obj.order_by}','${obj.remark}','${obj.create_time}','${obj.create_user}','${obj.create_organize}');
</#list>
