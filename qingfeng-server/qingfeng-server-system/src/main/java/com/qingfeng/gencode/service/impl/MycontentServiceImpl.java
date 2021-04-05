package com.qingfeng.gencode.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.entity.QueryRequest;
import com.qingfeng.common.service.IUploadService;
import com.qingfeng.entity.common.UploadFile;
import com.qingfeng.entity.gencode.Mycontent;
import com.qingfeng.gencode.service.IMycontentService;
import com.qingfeng.gencode.mapper.MycontentMapper;
import com.qingfeng.utils.DateTimeUtil;
import com.qingfeng.utils.GuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qingfeng
 * @title: MycontentServiceImpl
 * @projectName qingfeng-cloud
 * @description: TODO
 * @date 2021/3/8 000821:13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MycontentServiceImpl extends ServiceImpl<MycontentMapper, Mycontent> implements IMycontentService {

    @Autowired
    private IUploadService uploadService;

    /**
     * @title findListPage
     * @description 查询数据分页列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:57
     */
    public IPage<Mycontent> findListPage(Mycontent mycontent, QueryRequest request){
        Page<Mycontent> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findListPage(page, mycontent);
    }

    /**
     * @title findListPage
     * @description 查询数据列表
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:58
     */
    public List<Mycontent> findList(Mycontent mycontent){
        return this.baseMapper.findList(mycontent);
    }

    /**
     * @title saveMycontent
     * @description 保存数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:58
     */
    public void saveMycontent(Mycontent mycontent){
        // 创建信息
        String id = GuidUtil.getUuid();
        mycontent.setId(id);
        String time = DateTimeUtil.getDateTimeStr();
        mycontent.setCreate_time(time);
        mycontent.setType("1");
        mycontent.setStatus("0");
        //处理数据权限
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        mycontent.setCreate_user(authParams.split(":")[1]);
        mycontent.setCreate_organize(authParams.split(":")[2]);
        this.save(mycontent);

        //处理简介附件信息-更新信息主要意义在于可删选删除垃圾图片
        UploadFile uploadFile = new UploadFile();
        uploadFile.setObj_id(id);
        uploadFile.setUpdate_user(authParams.split(":")[1]);
        uploadFile.setUpdate_time(time);
        String[] file_intro_ids = mycontent.getIntro().split(",");
        for (String file_intro_id:file_intro_ids) {
            uploadFile.setId(file_intro_id);
            uploadService.updateById(uploadFile);
        }
    }

    /**
     * @title updateMycontent
     * @description 更新数据
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:58
     */
    public void updateMycontent(Mycontent mycontent){
        // 更新信息
        String id = mycontent.getId();
        String time = DateTimeUtil.getDateTimeStr();
        String authParams = SecurityContextHolder.getContext().getAuthentication().getName();
        mycontent.setUpdate_time(time);
        mycontent.setUpdate_user(authParams.split(":")[1]);
        this.updateById(mycontent);
        //处理简介附件信息-更新信息主要意义在于可删选删除垃圾图片
        UploadFile uploadFile = new UploadFile();
        uploadFile.setObj_id(id);
        uploadFile.setUpdate_user(authParams.split(":")[1]);
        uploadFile.setUpdate_time(time);
        String[] file_intro_ids = mycontent.getIntro().split(",");
        for (String file_intro_id:file_intro_ids) {
            uploadFile.setId(file_intro_id);
            uploadService.updateById(uploadFile);
        }
    }


}