package com.qingfeng.gencode.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.entity.gencode.Mytree;
import com.qingfeng.gencode.service.IMytreeService;
import com.qingfeng.gencode.mapper.MytreeMapper;
import com.qingfeng.utils.DateTimeUtil;
import com.qingfeng.utils.GuidUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qingfeng
 * @title: MytreeServiceImpl
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MytreeServiceImpl extends ServiceImpl<MytreeMapper, Mytree> implements IMytreeService {


    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:58
     */
    public IPage<Mytree> findListPage(Mytree mytree, QueryRequest request){
        Page<Mytree> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, mytree);
    }

    /**
     * @title findList
     * @description 查询数据列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:59
     */
    public List<Mytree> findList(Mytree mytree){
        return this.baseMapper.findList(mytree);
    }

    /**
     * @title saveMytree
     * @description 保存数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:59
     */
    public void saveMytree(Mytree mytree){
        // 创建信息
        String id = GuidUtil.getUuid();
        mytree.setId(id);
        String time = DateTimeUtil.getDateTimeStr();
        mytree.setCreate_time(time);
        mytree.setType("1");
        mytree.setStatus("0");
        //处理数据权限
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        mytree.setCreate_user(authParams.split(":")[1]);
        mytree.setCreate_organize(authParams.split(":")[2]);
        this.save(mytree);
    }

    /**
     * @title updateMytree
     * @description 更新数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:59
     */
    public void updateMytree(Mytree mytree){
        // 更新信息
        String time = DateTimeUtil.getDateTimeStr();
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        mytree.setUpdate_time(time);
        mytree.setUpdate_user(authParams.split(":")[1]);
        this.updateById(mytree);
    }


}