/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: PaymentClassControl
 * Author:   宣佚
 * Date:     2018/7/14 0014 上午 8:40
 * Description: 支出类别的数据库操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package control.classControl;

import database.DBHelper;
import javaBean.PaymentClass;
import javaBean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈支出类别的数据库操作〉
 *
 * @author 宣佚
 * @create 2018/7/14 0014
 * @since 1.0.0
 */
public class PaymentClassControl {
    /**
     * 功能描述: <br>
     * 〈插入支出类别〉
     *
     * @param paymentClass
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 上午 10:32
     */
    public void addClass(PaymentClass paymentClass)throws SQLException {
        Connection conn=DBHelper.getConnection();
        String sql="INSERT INTO paymentClass (className,userId)VALUES(?,?)";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,paymentClass.getClassName());
        ptmt.setInt(2,paymentClass.getUserId());
        ptmt.execute();
    }
/**
 * 功能描述: <br>
 * 〈删除支出类别〉
 *
 * @param paymentClass
 * @return:void
 * @since: 1.0.0
 * @Author:宣佚
 * @Date: 2018/7/14 0014 上午 10:37
 */
    public void deleteClass(PaymentClass paymentClass)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="DELETE FROM paymentClass WHERE id=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1, paymentClass.getId());
        ptmt.execute();
    }
    /**
     * 功能描述: <br>
     * 〈修改支出类别〉
     *
     * @param paymentClass
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 上午 10:40
     */
    public void updateClass(PaymentClass paymentClass)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="UPDATE paymentClass SET className=? WHERE id=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,paymentClass.getClassName());
        ptmt.setInt(2,paymentClass.getId());
        ptmt.execute();
    }
/**
 * 功能描述: <br>
 * 〈查询指定用户和支出类别名称的支出类别〉
 *
 * @param paymentClass
 * @return:paymentClass
 * @since: 1.0.0
 * @Author:宣佚
 * @Date: 2018/7/14 0014 下午 13:38
 */
    public PaymentClass queryClass(PaymentClass paymentClass)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="SELECT id FROM paymentClass WHERE className=? AND userId=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,paymentClass.getClassName());
        ptmt.setInt(2,paymentClass.getUserId());
        ResultSet rs=ptmt.executeQuery();
        if(rs.next()){
            PaymentClass paymentClass_query=new PaymentClass();
            paymentClass_query.setId(rs.getInt("id"));
            paymentClass_query.setClassName(paymentClass.getClassName());
            paymentClass_query.setUserId(paymentClass.getUserId());
            return paymentClass_query;
        }
        return null;
    }
    /**
     * 功能描述: <br>
     * 〈根据指定id查询支出种类〉
     *
     * @param paymentClass
     * @return:paymentClass
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/15 0015 上午 10:15
     */
    public PaymentClass queryClassById(PaymentClass paymentClass)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="SELECT className,userId FROM paymentClass WHERE id=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1,paymentClass.getId());
        ResultSet rs=ptmt.executeQuery();
        if(rs.next()){
            PaymentClass paymentClass_query=new PaymentClass();
            paymentClass_query.setUserId(rs.getInt("userId"));
            paymentClass_query.setClassName(rs.getString("className"));
            paymentClass_query.setId(paymentClass.getId());
            return paymentClass_query;
        }
        return null;
    }
    /**
     * 功能描述: <br>
     * 〈查询指定用户的支出类别〉
     *
     * @param user
     * @return:arraylist
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 上午 10:48
     */
    public ArrayList<PaymentClass> queryAllClass(User user)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="SELECT id,className FROM paymentClass WHERE userId=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1,user.getId());
        ResultSet rs=ptmt.executeQuery();
        ArrayList<PaymentClass>arrayList=new ArrayList<>();
        while(rs.next()){
            PaymentClass paymentClass=new PaymentClass();
            paymentClass.setClassName(rs.getString("className"));
            paymentClass.setId(rs.getInt("id"));
            paymentClass.setUserId(user.getId());
            arrayList.add(paymentClass);
        }
        return arrayList;
    }
    /**
     * 功能描述: <br>
     * 〈测试用例〉
     *
     * @param args
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 上午 10:54
     */
    public static void main(String args[]) {
        User user = new User();
        PaymentClassControl paymentClassControl=new PaymentClassControl();
        PaymentClass paymentClass=new PaymentClass();
        paymentClass.setId(1);
        paymentClass.setUserId(1);
        paymentClass.setClassName("吃饭");
        user.setId(1);
        user.setUsername("李宣佚");
        user.setPassword("lxy991120");
        user.setSignature("李宣佚好帅");
        try {
            /*paymentClassControl.addClass(paymentClass);
            ArrayList<PaymentClassService>arrayList=paymentClassControl.queryAllClass(user);
            for (PaymentClassService p:arrayList) {
                System.out.println(p.getClassName()+" "+p.getId()+" "+ p.getUserId());
            }*/
            PaymentClass p=paymentClassControl.queryClass(paymentClass);
            System.out.println(p.getClassName()+" "+p.getId()+" "+ p.getUserId());
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
