package com.qingfeng.utils;

import java.io.*;

/**
 * @ProjectName FileExport
 * @author Administrator
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/4/3 0003 19:53
 */
public class FileExport {

    /** 
     * @Description: creatFile 创建文件
     * @Param: [path] 
     * @return: boolean 
     * @Author: anxingtao
     * @Date: 2020-9-28 22:23 
     */ 
    public static boolean creatFile(String path) throws IOException {
        boolean flag = false;
        File filename = new File(path);
        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }
        return flag;
    }


    public static boolean writeFile(String path,String content) throws IOException {
        // 文件路径
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }

        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = content + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }




}
