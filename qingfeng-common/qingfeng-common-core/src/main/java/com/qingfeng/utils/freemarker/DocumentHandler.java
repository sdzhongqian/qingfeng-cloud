//package com.qingfeng.utils.freemarker;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//
//import java.io.*;
//import java.util.Map;
//
///**
// * @Title: DocumentHandler
// * @ProjectName qingfeng
// * @Description: word导出
// * @author anxingtao
// * @date 2020-9-20 9:53
// */
//public class DocumentHandler {
//    private Configuration configuration = null;
//    private static final String templateFolder = "D:/";
//    public DocumentHandler() {
//        configuration = new Configuration();
//        configuration.setDefaultEncoding("utf-8");
//    }
//
//    public void createDoc(Map<String,Object> dataMap,String fileName) throws IOException {
//        //dataMap 要填入模本的数据文件
//        //设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
//        //这里我们的模板是放在template包下面
////        configuration.setDirectoryForTemplateLoading(new File(templateFolder));
//        configuration.setClassForTemplateLoading(this.getClass(),"/docTemp");
//        Template t=null;
//        try {
//            //test.ftl为要装载的模板
//            t = configuration.getTemplate("oweLetter.ftl");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //输出文档路径及名称
//        File outFile = new File(fileName);
//        Writer out = null;
//        FileOutputStream fos=null;
//        try {
//            fos = new FileOutputStream(outFile);
//            OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");
//            //这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。
//            //out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
//            out = new BufferedWriter(oWriter);
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//        }
//
//        try {
//            t.process(dataMap, out);
//            out.close();
//            fos.close();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //System.out.println("---------------------------");
//    }
//}
