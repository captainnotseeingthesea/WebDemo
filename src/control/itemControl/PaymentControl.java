/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: PaymentControl
 * Author:   宣佚
 * Date:     2018/7/14 0014 下午 13:40
 * Description: 支出条目的数据库操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package control.itemControl;

import database.DBHelper;
import javaBean.Payment;
import javaBean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈支出条目的数据库操作〉
 *
 * @author 宣佚
 * @create 2018/7/14 0014
 * @since 1.0.0
 */
public class PaymentControl {
    /**
     * 功能描述: <br>
     * 〈增加支付条目〉
     *
     * @param payment
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 下午 14:17
     */
    public void addPayment(Payment payment)throws SQLException {
        Connection conn=DBHelper.getConnection();
        String sql="INSERT INTO payment (classId,money,paytime,userId)VALUES(?,?,?,?)";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1,payment.getClassId());
        ptmt.setDouble(2,payment.getMoney());
        ptmt.setString(3,payment.getPayTime());
        ptmt.setInt(4,payment.getUserId());
        ptmt.execute();
    }
    /**
     * 功能描述: <br>
     * 〈删除支出条目，置mark=1〉
     *
     * @param payment
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 下午 14:28
     */
    public void deletePayment(Payment payment)throws SQLException{
        Connection connection=DBHelper.getConnection();
        String sql="UPDATE payment SET mark=1 WHERE id=?";
        PreparedStatement ptmt=connection.prepareStatement(sql);
        ptmt.setInt(1,payment.getId());
        ptmt.execute();
    }
    /**
     * 功能描述: <br>
     * 〈对支付条目进行更改〉
     *
     * @param payment
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 下午 14:40
     */
    public void updatePayment(Payment payment)throws  SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="UPDATE payment SET classId=?,money=?,payTime=? WHERE id=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1,payment.getClassId());
        ptmt.setDouble(2,payment.getMoney());
        ptmt.setString(3,payment.getPayTime());
        ptmt.setInt(4,payment.getId());
        ptmt.execute();
    }
/**
 * 功能描述: <br>
 * 〈查询指定用户的全部的支出条目〉
 *
 * @param user
 * @return:arraylist
 * @since: 1.0.0
 * @Author:宣佚
 * @Date: 2018/7/16 0016 下午 14:52
 */
    public ArrayList<Payment> queryAllItems(User user)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="SELECT id,classId,money,payTime FROM payment WHERE userId=? AND mark=0";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1,user.getId());
        ResultSet rs=ptmt.executeQuery();
        ArrayList<Payment>arrayList=new ArrayList<Payment>();
        while(rs.next()){
            Payment payment=new Payment();
            payment.setId(rs.getInt("id"));
            payment.setClassId(rs.getInt("classId"));
            payment.setMoney(rs.getDouble("money"));
            payment.setPayTime(rs.getString("payTime"));
            payment.setUserId(user.getId());
            arrayList.add(payment);
        }
        return arrayList;
    }

    public static void main(String args[]){
        PaymentControl paymentControl=new PaymentControl();
        Payment payment=new Payment();
        payment.setClassId(4);
        payment.setMoney(2131.12);
        payment.setUserId(1);
        payment.setId(1);
        try {
            paymentControl.deletePayment(payment);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
