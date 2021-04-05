package com.qingfeng.utils;


import java.io.File;

/**
 * @ProjectName PathUtil
 * @author Administrator
 * @version 1.0.0
 * @Description 在windows和linux系统下均可正常使用
 * @createTime 2021/4/3 0003 19:54
 */
public class PathUtil {
    //获取项目的根路径
    public final static String classPath;

    static {
        //获取的是classpath路径，适用于读取resources下资源
        classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }

    /** 
     * @Description: 获取系统路径
     * @Param: [] 
     * @return: java.lang.String 
     * @Author: anxingtao
     * @Date: 2020-10-11 21:03 
     */ 
    public static String getSystemPath() {
        return SystemPath("");
    }

    /**
     * 项目根目录
     */
    public static String getRootPath() {
        return RootPath("");
    }

    /**
     * 自定义追加路径
     */
    public static String getRootPath(String u_path) {
        return RootPath("/" + u_path);
    }

    /**
     * 私有处理方法
     */
    private static String RootPath(String u_path) {
        String rootPath = "";
        //windows下
        if ("\\".equals(File.separator)) {
            //System.out.println(classPath);
            rootPath = classPath + u_path;
            rootPath = rootPath.replaceAll("/", "\\\\");
            if (rootPath.substring(0, 1).equals("\\")) {
                rootPath = rootPath.substring(1);
            }
        }
        //linux下
        if ("/".equals(File.separator)) {
            //System.out.println(classPath);
            rootPath = classPath + u_path;
            rootPath = rootPath.replaceAll("\\\\", "/");
        }
        return rootPath;
    }

    private static String SystemPath(String u_path) {
        String rootPath = "";
        //windows下
        if ("\\".equals(File.separator)) {
            //System.out.println(classPath);
            rootPath = "C://" + u_path;
        }
        //linux下
        if ("/".equals(File.separator)) {
            rootPath = "/" + u_path;
        }
        return rootPath;
    }


}