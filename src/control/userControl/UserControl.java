/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: UserControl
 * Author:   宣佚
 * Date:     2018/7/12 0012 上午 9:37
 * Description: 用户的增删改查
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package control.userControl;

import database.DBHelper;
import encrypt.EncryptPassword;
import javaBean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户的增删改查〉
 *
 * @author 宣佚
 * @create 2018/7/12 0012
 * @since 1.0.0
 */
public class UserControl {
    /**
     * 功能描述: <br>
     * 〈增加用户〉
     *
     * @param user
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/12 0012 上午 10:13
     */
    public void addUser(User user)throws SQLException {
        Connection conn=DBHelper.getConnection();
        String sql="INSERT INTO user (username,password) VALUES (?,?)";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,user.getUsername());
        ptmt.setString(2,EncryptPassword.encryptPassword(user.getPassword()));
        ptmt.execute();
    }

    /**
     * 功能描述: <br>
     * 〈用户的查询〉
     *
     * @param user
     * @return:user
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/12 0012 上午 10:14
     */
    public User queryUser(User user)throws SQLException{
        Connection conn=DBHelper.getConnection();
        User query_user=new User();
        String sql="SELECT password,signature FROM user WHERE username=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,user.getUsername());
        ResultSet rs=ptmt.executeQuery();
        if(rs.next()){
            query_user.setUsername(user.getUsername());
            query_user.setPassword(rs.getString("password"));
            query_user.setSignature(rs.getString("signature"));
            return query_user;
        }
        return null;
    }
    /**
     * 功能描述: <br>
     * 〈对用户的密码进行更改〉
     *
     * @param user
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/12 0012 下午 23:22
     */
    public void updatePassword(User user)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="UPDATE user SET password=? WHERE username=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,EncryptPassword.encryptPassword(user.getPassword()));
        ptmt.setString(2,user.getUsername());
        ptmt.execute();
    }
    /**
     * 功能描述: <br>
     * 〈更改用户的个性签名〉
     *
     * @param user
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/13 0013 上午 11:37
     */
    public void updateSignature(User user)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="UPDATE user SET signature=? WHERE username=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,user.getSignature());
        ptmt.setString(2,user.getUsername());
        ptmt.execute();
    }
    /**
     * 功能描述: <br>
     * 〈测试方法〉
     *
     * @param args
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/12 0012 上午 10:13
     */
    public static void main(String args[]){
        User user=new User();
        UserControl userControl=new UserControl();
        user.setUsername("lxy");
        user.setPassword("lxy991120");
        user.setSignature("李宣佚真的好帅啊");
        try {
            userControl.updateSignature(user);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
