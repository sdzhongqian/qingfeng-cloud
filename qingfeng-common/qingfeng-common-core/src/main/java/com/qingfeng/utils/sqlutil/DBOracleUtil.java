package com.qingfeng.utils.sqlutil;


import java.sql.*;

/**
 * @ProjectName DBOracleUtil
 * @author Administrator
 * @version 1.0.0
 * @Description Oracle链接工具类
 * @createTime 2021/4/3 0003 19:28
 */
public class DBOracleUtil {

    private static String dbDriver = "oracle.jdbc.driver.OracleDriver";
    private String dbConnectionURL = "";
    private String dbUsername = "";//oracle数据库的用户名
    private String dbPassword = "";//oracle数据库的用户密码
    private PreparedStatement sta = null;
    private ResultSet rs = null;
    private Connection conn = null;


    public DBOracleUtil(String dbConnectionURL, String dbUsername,String dbPassword){
        this.dbConnectionURL = dbConnectionURL;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }


    /**
     * @return 连接对象
     */
    public void getConn() {
        try {
            //加载驱动程序
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbConnectionURL, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param sql
     *            sql语句  增加，删除，修改
     * @param obj
     *            参数
     * @return
     */
    public int update(String sql, Object... obj) {
        int count = 0;
        try {
            sta = conn.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    sta.setObject(i + 1, obj[i]);
                }
            }
            count = sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{

            close();
        }
        return count;
    }

    /**
     * @param sql sql语句
     * @param obj 参数
     * @return 数据集合
     */
    public ResultSet select(String sql,Object...obj){
        try {
            sta=conn.prepareStatement(sql);
            if(obj!=null){
                for(int i=0;i<obj.length;i++){
                    sta.setObject(i+1, obj[i]);
                }
            }
            rs=sta.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 关闭资源
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sta != null) {
                    sta.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
