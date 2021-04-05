package com.qingfeng.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName FileUtil
 * @author Administrator
 * @version 1.0.0
 * @Description 附件下载类
 * @createTime 2021/4/3 0003 19:52
 */
public class FileUtil {
	
	private static final int BUFFERED_SIZE = 4 * 1024;
	
	/**
	 * 下载文件
	 */
	public static void downFile(HttpServletResponse response, String filePath,String filename) throws Exception {
		File tempFile = new File(filePath);
		System.out.println(tempFile.exists());
		if (tempFile.exists()) {
			response.reset();
			response.setContentType("bin");//
			filename = new String(filename.getBytes(), "ISO-8859-1");
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.addHeader("Content-Length", "" + tempFile.length());
			response.setContentType("application/octet-stream");

			FileInputStream fis = new FileInputStream(tempFile);
			OutputStream os = response.getOutputStream();
			byte[] bb = new byte[1024*8];
			int i = 0;
			while ((i = fis.read(bb)) > 0) {
				os.write(bb, 0, i);
			}
			os.close();
			os.flush();
			fis.close();
		}
	}
	
	/**
	 * 复制文件
	 * @param src
	 * @param target
	 */
	public static void copy(File src, File target) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFERED_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(target), BUFFERED_SIZE);
            byte[] bs = new byte[BUFFERED_SIZE];
            int i;
            while ((i = in.read(bs)) > 0) {
                out.write(bs, 0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null){
					in.close();
				}
                if (out != null){
					out.close();
				}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	/**保存文件
     * @param stream
     * @param path
     * @param filename
     * @throws IOException
     */
    public static void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException
    {      
        FileOutputStream fs=new FileOutputStream( path + "/"+ filename);
        byte[] buffer =new byte[1024*1024];
        int bytesum = 0;
        int byteread = 0; 
        while ((byteread=stream.read(buffer))!=-1)
        {
           bytesum+=byteread;
           fs.write(buffer,0,byteread);
           fs.flush();
        } 
        fs.close();
        stream.close();      
    }  
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// 创建目录
	   String dirName = "d:/FHSysPicture/topic/";
	   FileUtil.createDir(dirName);
	}
	
	/**
	* 创建目录
	* @param destDirName 目标目录名
	* @return 目录创建成功返回true，否则返回false
	*/
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if(dir.exists()) {
			return false;
		}
		if(!destDirName.endsWith(File.separator)){
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if(dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**  
     *  删除文件  
     *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt  
     *  @return  boolean
     */
   public static void delFile(String filePathAndName)  {
       try  {  
           String filePath  =  filePathAndName;
           filePath  =  filePath.toString();  
           File myDelFile  =  new File(filePath);
           myDelFile.delete();  
 
       }  
       catch  (Exception e)  {
           System.out.println("删除文件操作出错");
           e.printStackTrace();  
 
       }  
 
   }



	/**
	 * @description 不使用递归的方法调用
	 * @param path 文件夹路径
	 * @return java.util.List<java.io.File>
	 * @author https://blog.csdn.net/chen_2890
	 * @date 2019/6/14 17:34
	 * @version V1.0
	 */
	public static List<File> traverseFolder1(String path) {
		List<File> fileList = new ArrayList<File>();
		int fileNum = 0, folderNum = 0;
		File file = new File(path);
		if (file.exists()) {
			LinkedList<File> list = new LinkedList<File>();
			File[] files = file.listFiles();
			for (File file2 : files) {
				if (file2.isDirectory()) {
					System.out.println("文件夹:" + file2.getAbsolutePath());
					list.add(file2);
					folderNum++;
				} else {
					fileList.add(file2);
					System.out.println("文件:" + file2.getAbsolutePath());
					fileNum++;
				}
			}
			File temp_file;
			while (!list.isEmpty()) {
				temp_file = list.removeFirst();
				files = temp_file.listFiles();
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
						list.add(file2);
						folderNum++;
					} else {
						fileList.add(file2);
						System.out.println("文件:" + file2.getAbsolutePath());
						fileNum++;
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
		System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
		return fileList;
	}
	/**
	 * @description 使用递归的方法调用
	 * @param path 文件夹路径
	 * @return java.util.List<java.io.File>
	 * @author https://blog.csdn.net/chen_2890
	 * @date 2019/6/14 17:35
	 * @version V1.0
	 */
	public static List<File> traverseFolder2(String path) {
		List<File> fileList = new ArrayList<>();
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (null == files || files.length == 0) {
				System.out.println("文件夹是空的!");
				return null;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
						traverseFolder2(file2.getAbsolutePath());
					} else {
						fileList.add(file2);
						System.out.println("文件:" + file2.getAbsolutePath());
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
		return fileList;
	}

	/**
	 * @description 使用递归的方法调用，并判断文件名是否以.jpg结尾
	 * @param path 文件夹路径
	 * @return java.util.List<java.io.File>
	 * @author https://blog.csdn.net/chen_2890
	 * @date 2019/6/14 17:35
	 * @version V1.0
	 */
	public static List<File> getFileList(String path) {
		List<File> fileList = new ArrayList<>();
		File dir = new File(path);
		// 该文件目录下文件全部放入数组
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				// 判断是文件还是文件夹
				if (files[i].isDirectory()) {
					// 获取文件绝对路径
					getFileList(files[i].getAbsolutePath());
					// 判断文件名是否以.jpg结尾
				} else if (fileName.endsWith(".jpg")) {
					String strFileName = files[i].getAbsolutePath();
					System.out.println("---" + strFileName);
					fileList.add(files[i]);
				} else {
					continue;
				}
			}
		}
		return fileList;
	}

	/** 
	 * @Description: readFileContent 
	 * @Param: [file] 
	 * @return: java.lang.String 
	 * @Author: anxingtao
	 * @Date: 2020-10-16 11:14 
	 */ 
	public static String readFileContent(File file) {
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr+"\n");
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}

}