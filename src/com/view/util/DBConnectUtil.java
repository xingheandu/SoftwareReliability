package com.view.util;
/*
 *Created by haseeon 2018/12/31.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.view.login.NoticeDialog;
import com.view.login.UserBean;




public class DBConnectUtil {
    static String driver = "com.mysql.jdbc.Driver";
    static String ip="39.106.170.21";
    static int port=3306;
    static String dbName="ruankao";
    static String url="jdbc:mysql://"+ip+":"+port+"/"+dbName;
    static String userName="xjb";
    static String password="123456";

    public static int addUser (String name, String pwd)  {
        Connection con = getConn();
        String sql  = "insert into user (name,password) values (?,?)" ;
        PreparedStatement pstmt;
        int i = 0;
        try {
            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            i = pstmt.executeUpdate();
            pstmt.close();
            con.close();
        }
        catch (SQLException e) {
            new NoticeDialog("数据库连接失败");
            e.printStackTrace();
        }
        return  i;
    }
    public static UserBean selectUser(String name){
        Connection connection = getConn();
        String sql = "select * from user where name=?";
        PreparedStatement pstmt;

        UserBean userBean = null;
        try {
            pstmt = (PreparedStatement)connection.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                userBean = new UserBean(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
            }
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  userBean;
    }
    public static void getAll(){
        Connection conn = getConn();
        String sql = "select * from user";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private static Connection getConn(){
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            if ( ! con.isClosed()){
                System.out.println("Succeeded connecting to the Database!");
            }
        } catch (ClassNotFoundException e) {
            new NoticeDialog("JDBC连接失败");
            e.printStackTrace();
        } catch (SQLException e) {
            new NoticeDialog("数据库连接失败");
            e.printStackTrace();
        }
        return con;
    }
}
