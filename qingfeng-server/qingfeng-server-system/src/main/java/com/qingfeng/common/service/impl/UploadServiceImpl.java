package com.qingfeng.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.common.mapper.UploadMapper;
import com.qingfeng.common.service.IUploadService;
import com.qingfeng.entity.common.UploadFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName UploadServiceImpl
 * @author qingfeng
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 20:29
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UploadServiceImpl extends ServiceImpl<UploadMapper, UploadFile> implements IUploadService {


}