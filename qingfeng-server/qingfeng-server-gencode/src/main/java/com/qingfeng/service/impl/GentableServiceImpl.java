package com.qingfeng.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.gencode.Genfield;
import com.qingfeng.entity.gencode.Gentable;
import com.qingfeng.mapper.GentableMapper;
import com.qingfeng.service.IGenfieldService;
import com.qingfeng.service.IGentableService;
import com.qingfeng.properties.ServerGencodeProperties;
import com.qingfeng.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @ProjectName GentableServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description GentableServiceImpl
 * @createTime 2021/4/3 0003 20:01
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GentableServiceImpl extends ServiceImpl<GentableMapper, Gentable> implements IGentableService {

    @Autowired
    private ServerGencodeProperties serverGencodeProperties;
    @Autowired
    private IGenfieldService genfieldService;

    private static String table_schema = "qingfeng_cloud";

    /**
     * @title findTableListPage
     * @description 查询数据表分页
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:01
     */
    public List<PageData> findTableListPage(PageData pd){
        pd.put("table_schema",table_schema);
        pd.put("pageNum",(Integer.parseInt(pd.get("pageNum").toString())-1)*Integer.parseInt(pd.get("pageSize").toString()));
        return this.baseMapper.findTableListPage(pd);
    }

    /**
     * @title findTableListNum
     * @description 查询数据表梳理
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:02
     */
    public Integer findTableListNum(PageData pd){
        return this.baseMapper.findTableListNum(pd);
    }

    /**
     * @title findTableList
     * @description 查询数据表列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:02
     */
    public List<PageData> findTableList(PageData pd){
        return this.baseMapper.findTableList(pd);
    }

    /**
     * @title saveTable
     * @description 保存数据表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:02
     */
    public void saveTable(PageData pd){
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        String user_id = userParams.split(":")[1];
        String organize_id = userParams.split(":")[2];
        pd.put("table_schema",table_schema);
        pd.put("table_names", Arrays.asList(pd.get("table_names").toString().split(",")));
        List<PageData> list = this.baseMapper.findTableList(pd);
        String time = DateTimeUtil.getDateTimeStr();
        List<Gentable> gentables = new ArrayList<Gentable>();
        if(list.size()>0){
            for (PageData param:list) {
                String[] table_name = param.get("table_name").toString().split("_");
                String id = GuidUtil.getUuid();
                Gentable gentable = new Gentable();
                gentable.setId(id);
                gentable.setTable_name(param.get("table_name").toString());
                gentable.setTable_comment(param.get("table_comment").toString());
                gentable.setType("0");
                gentable.setTemp_type("0");
                gentable.setPack_path("com.qingfeng");
                gentable.setMod_name(table_name[0]);
                String bus_name = "";
                if(table_name.length>1){
                    for (int i = 1; i < table_name.length; i++) {
                        if(i==1){
                            bus_name+= table_name[1];
                        }else{
                            bus_name+= StrUtil.upperFirst(table_name[i]);
                        }
                    }
                }else{
                    bus_name = table_name[0];
                }
                gentable.setTree_id(id);
                gentable.setBus_name(bus_name);
                gentable.setMenu_name(param.get("table_comment").toString());
                gentable.setGen_type("0");
                gentable.setGen_path(PathUtil.getSystemPath());
                gentable.setStatus_type("0");
                gentable.setOrder_by("1");
                gentable.setCreate_time(time);
                gentable.setCreate_user(user_id);
                gentable.setCreate_organize(organize_id);

                gentables.add(gentable);
                //生成数据字段表
                pd.put("table_schema",table_schema);
                pd.put("table_name",param.get("table_name"));
                List<PageData> fieldList = this.baseMapper.findColumndList(pd);
                List<Genfield> genfields = new ArrayList<Genfield>();
                if(fieldList.size()>0){
                    for (PageData fieldParam:fieldList) {
                        Genfield genfield = new Genfield();
                        genfield.setId(GuidUtil.getUuid());
                        genfield.setType("0");
                        genfield.setTable_id(id);
                        genfield.setField_name(fieldParam.get("column_name").toString());
                        genfield.setField_comment(fieldParam.get("column_name").toString());
                        genfield.setField_type(fieldParam.get("data_type").toString());

                        if(serverGencodeProperties.getGencodeField().contains(fieldParam.get("column_name").toString())){
                            genfield.setField_operat("N");
                            genfield.setField_list("N");
                        }else{
                            genfield.setField_operat("Y");
                            genfield.setField_list("Y");
                        }
                        if(fieldParam.get("is_nullable").equals("NO")){
                            genfield.setVerify_rule("required");
                        }else{
                            genfield.setVerify_rule("");
                        }
                        genfield.setField_query("N");
                        genfield.setQuery_type("");
                        genfield.setShow_type("1");
                        genfield.setOrder_by(fieldParam.get("ordinal_position").toString());
                        genfield.setRemark(fieldParam.get("column_type").toString());
                        genfield.setCreate_time(time);
                        genfield.setCreate_user(user_id);
                        genfield.setCreate_organize(organize_id);
                        genfields.add(genfield);
                    }
                }
                genfieldService.saveBatch(genfields);
            }
        }
        this.saveBatch(gentables);
    }

    /**
     * @title findListPage
     * @description 查询数据集合
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:02
     */
    public IPage<Gentable> findListPage(Gentable gentable, QueryRequest request){
        Page<Gentable> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, gentable);
    }

    /**
     * @title del
     * @description 删除
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:02
     */
    public void del(String ids){
        String[] del_ids = ids.split(",");
        for (String del_id: del_ids) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("table_id",del_id);
            genfieldService.remove(queryWrapper);
        }
        this.removeByIds(Arrays.asList(del_ids));
    }

    /**
     * @title updateTable
     * @description 更新数据表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:03
     */
    public void updateTable(Gentable gentable){
        String time = DateTimeUtil.getDateTimeStr();
        String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
        String user_id = userParams.split(":")[1];
        String organize_id = userParams.split(":")[2];
        gentable.setUpdate_user(user_id);
        gentable.setUpdate_time(time);
        this.updateById(gentable);

        List<Genfield> genfieldList = gentable.getFieldList();
        for (Genfield genfield:genfieldList) {
            if(genfield.getField_operat().equals("true")){
                genfield.setField_operat("Y");
            }else{
                genfield.setField_operat("N");
            }
            if(genfield.getField_list().equals("true")){
                genfield.setField_list("Y");
            }else{
                genfield.setField_list("N");
            }
            if(genfield.getField_query().equals("true")){
                genfield.setField_query("Y");
            }else{
                genfield.setField_query("N");
            }
            genfield.setUpdate_time(time);
            genfield.setUpdate_user(user_id);
            genfieldService.updateById(genfield);
        }
    }

    /**
     * @title findMenuInfo
     * @description 查询菜单详情
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:03
     */
    public PageData findMenuInfo(PageData pd){
        return this.baseMapper.findMenuInfo(pd);
    }


}