/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: DBHelper
 * Author:   宣佚
 * Date:     2018/7/12 0012 上午 9:13
 * Description: connext to mysql database
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package database;


import java.sql.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈connect to mysql database〉
 *
 * @author 宣佚
 * @create 2018/7/12 0012
 * @since 1.0.0
 */
public class DBHelper {
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
    private static final String username="root";
    private static final String password="991227";
    private static Connection conn=null;
    //  连接驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 单例模式返回数据库连接对象
    public static Connection getConnection() throws SQLException {
        if (conn == null)
            conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    public static void main(String args[]){
        String sql="select username,password from user";
        try {
            conn=DBHelper.getConnection();
            if(conn!=null){
                System.out.println("数据库连接正常");
                PreparedStatement ptmt=conn.prepareStatement(sql);
                ResultSet rs=ptmt.executeQuery();
                while (rs.next()){
                    System.out.println(rs.getString("username")+" "+rs.getString("password"));
                }
            }else{
                System.out.println("数据库连接异常");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
