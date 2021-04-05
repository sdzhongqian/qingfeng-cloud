package com.qingfeng.common.controller;

import com.qingfeng.base.controller.BaseController;
import com.qingfeng.common.service.IUploadService;
import com.qingfeng.entity.MyResponse;
import com.qingfeng.entity.common.UploadFile;
import com.qingfeng.exception.MyException;
import com.qingfeng.utils.*;
import com.qingfeng.utils.upload.ParaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @ProjectName UploadController
 * @author qingfeng
 * @version 1.0.0
 * @Description 附件上传
 * @createTime 2021/4/3 0003 20:27
 */
@Slf4j
@Validated
@RestController
@RequestMapping("upload")
public class UploadController extends BaseController {

    @Autowired
    public IUploadService uploadService;


    //=======================================================file 处理================================================

    /**
     * @title uploadFile
     * @description 上传附件
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:27
     */
    @ResponseBody
    @PostMapping("uploadFile")
    public MyResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        UploadFile uploadFile = new UploadFile();
        Json json = new Json();
        System.out.println(file);
        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
            String fileSuffix = myFileName.substring(myFileName.lastIndexOf("."));//文件类型
            String fileType = myFileName.substring(myFileName.lastIndexOf(".")+1);//文件后缀
            //保存文件
            String savePath = ParaUtil.localName;
            String path = ParaUtil.common+ DateTimeUtil.getDate()+"/"+ GuidUtil.getGuid()+fileSuffix;
            File files = new File(savePath+path);
            if (!files.getParentFile().exists()){
                files.getParentFile().mkdirs();
            }
            try {

                file.transferTo(files);
                uploadFile.setId(GuidUtil.getUuid());
                uploadFile.setName(myFileName);
                uploadFile.setDesnames(myFileName);
                uploadFile.setFile_path(path);
                uploadFile.setFile_type(fileType);
                uploadFile.setFile_size(files.length()+"");
                uploadFile.setFile_suffix(fileSuffix);

                String time = DateTimeUtil.getDateTimeStr();
                uploadFile.setCreate_time(time);
                //处理数据权限
                String userParams = SecurityContextHolder.getContext().getAuthentication().getName();
                //处理数据权限
                String user_id = userParams.split(":")[1];
                String organize_id = userParams.split(":")[2];
                uploadFile.setCreate_user(user_id);
                uploadFile.setCreate_organize(organize_id);
                uploadService.save(uploadFile);
                uploadFile.setShow_file_path(ParaUtil.cloudfile+path);
            } catch (IOException e) {
                json.setSuccess(false);
                json.setMsg("上传失败");
                e.printStackTrace();
            }
        }else{
            json.setSuccess(false);
            json.setMsg("文件为空");
        }
        return new MyResponse().data(uploadFile);
    }

    /**
     * @title delFile
     * @description 删除附件
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:27
     */
    @GetMapping("/delFile")
    public void delFile(@RequestParam String id, @RequestParam String file_path) throws MyException {
        try {
            //查询信息
            File pathFile = new File(ParaUtil.localName+file_path);
            pathFile.delete();
            pathFile.deleteOnExit();
            //删除数据
            uploadService.removeById(id);
        } catch (Exception e) {
            String message = "执行失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    /**
     * @title downloadFile
     * @description 下载附件
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:28
     */
    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam String file_path, @RequestParam String name,HttpServletResponse response) throws Exception {
        FileUtil.downFile(response,ParaUtil.localName+file_path,name);
    }



    //==========================================以下内容暂未用到==============================================================




    /**
     * @title uploadOnlyLocalFile
     * @description 上传本地不存储
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:28
     */
    @RequestMapping(value = "/uploadOnlyLocalFile", method = RequestMethod.POST)
    public void uploadOnlyLocalFile(HttpServletRequest request, HttpServletResponse response , HttpSession session, MultipartFile file) throws Exception {
        PageData pd = new PageData(request);
        String filename = file.getOriginalFilename();// 文件原名称
        String fileSuffix = filename.substring(filename.lastIndexOf("."));//文件类型
        String fileType = filename.substring(filename.lastIndexOf(".")+1);//文件后缀

        //保存文件
        String savePath = ParaUtil.localName;
        String path = ParaUtil.common+DateTimeUtil.getDate()+"/"+ GuidUtil.getGuid()+fileSuffix;
        File files = new File(savePath+path);
        if (!files.getParentFile().exists()){
            files.getParentFile().mkdirs();
        }
        file.transferTo(files);
        pd.put("name",filename);
        pd.put("desnames",filename);
        pd.put("file_path",path);
        pd.put("file_type",fileType);
        pd.put("file_size",files.length());
        pd.put("show_path",ParaUtil.cloudfile+path);
        Json json = new Json();
        json.setSuccess(true);
        json.setData(pd);
        json.setMsg("上传成功。");
        this.writeJson(response,json);
    }

    /**
     * @title delOnlyLocalFile
     * @description 删除本地附件
     * @author qingfeng
     * @updateTime 2021/4/3 0003 20:28
     */
    @RequestMapping(value = "/delOnlyLocalFile", method = RequestMethod.GET)
    public void delOnlyLocalFile(HttpServletRequest request, HttpServletResponse response , HttpSession session) throws Exception {
        PageData pd = new PageData(request);

        String savePath = ParaUtil.localName;
        File files = new File(savePath+pd.get("file_path"));
        files.delete();
        files.deleteOnExit();
        Json json = new Json();
        json.setSuccess(true);
        json.setMsg("文件删除成功。");
        this.writeJson(response,json);
    }


    /** 
     * @Description: downloadFile 
     * @Param: [request, response, session] 
     * @return: void 
     * @Author: qingfeng
     * @Date: 2019-3-11 11:05 
     */ 
//    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
//    public void downloadFile(HttpServletRequest request, HttpServletResponse response , HttpSession session) throws Exception {
//        PageData pd = new PageData(request);
////        String savePath = ParaUtil.xmAddress;
//        String savePath= request.getSession().getServletContext().getRealPath("/");
//        System.out.println("##################:"+savePath+pd.get("file_path").toString());
//        FileUtil.downFile(response,savePath+pd.get("file_path").toString(),pd.get("name").toString());
//    }



}
