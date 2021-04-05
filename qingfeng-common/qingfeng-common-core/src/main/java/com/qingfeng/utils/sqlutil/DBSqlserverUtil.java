package com.qingfeng.utils.sqlutil;

import java.sql.*;

/**
 * @ProjectName DBSqlserverUtil
 * @author Administrator
 * @version 1.0.0
 * @Description Sqlserver链接工具类
 * @createTime 2021/4/3 0003 19:28
 */
public class DBSqlserverUtil {

    private String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 驱动类类名
    //  jdbc:sqlserver://localhost:1433;DatabaseName=school","sa","tjw19951105"
    private String dbConnectionURL = "";// 连接URL
    private String dbUsername = "sa";// 数据库用户名
    private String dbPassword = "tjw19951105";// 数据库密码
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public DBSqlserverUtil(String dbConnectionURL, String dbUsername,String dbPassword){
        this.dbConnectionURL = dbConnectionURL;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public void getConnection() {
        try {
            Class.forName(dbDriver);// 注册驱动
            conn = DriverManager.getConnection(dbConnectionURL,dbUsername,
                    dbPassword);// 获得连接对象
            System.out.println("成功加载SQL Server驱动程序");
        } catch (ClassNotFoundException e) {// 捕获驱动类无法找到异常
            System.out.println("找不到SQL Server驱动程序");
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (SQLException e) {// 捕获SQL异常
            e.printStackTrace();
        }
    }


    public ResultSet select(String sql) throws Exception {
        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException sqle) {
            throw new SQLException("select data Exception: "
                    + sqle.getMessage());
        } catch (Exception e) {
            throw new Exception("System error: " + e.getMessage());
        }

    }

    /*
     * 增删改均调用这个方法
     */
    public void updata(String sql) throws Exception {
        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new SQLException("insert data Exception: "
                    + sqle.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                throw new Exception("ps close exception: " + e.getMessage());
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new Exception("conn close exception: " + e.getMessage());
            }
        }
    }


    public void close() throws Exception  {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            throw new Exception("ps close exception: " + e.getMessage());
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw new Exception("conn close exception: " + e.getMessage());
        }
    }

}
