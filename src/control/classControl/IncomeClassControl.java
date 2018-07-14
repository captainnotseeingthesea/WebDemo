/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: IncomeClassControl
 * Author:   宣佚
 * Date:     2018/7/14 0014 上午 8:41
 * Description: 收入类别的数据库操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package control.classControl;

import database.DBHelper;
import javaBean.Income;
import javaBean.IncomeClass;
import javaBean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈收入类别的数据库操作〉
 *
 * @author 宣佚
 * @create 2018/7/14 0014
 * @since 1.0.0
 */
public class IncomeClassControl {
    /**
     * 功能描述: <br>
     * 〈收入类别的增加〉
     *
     * @param incomeClass
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 下午 12:51
     */
       public void addClass(IncomeClass incomeClass)throws SQLException{
           Connection conn=DBHelper.getConnection();
           String sql="INSERT INTO incomeClass (className,userId)VALUES(?,?)";
           PreparedStatement ptmt=conn.prepareStatement(sql);
           ptmt.setString(1,incomeClass.getClassName());
           ptmt.setInt(2,incomeClass.getUserId());
           ptmt.execute();
       }
       /**
        * 功能描述: <br>
        * 〈删除收入类别〉
        *
        * @param incomeClass
        * @return:void
        * @since: 1.0.0
        * @Author:宣佚
        * @Date: 2018/7/14 0014 下午 12:56
        */
       public void deleteClass(IncomeClass incomeClass)throws SQLException{
           Connection conn=DBHelper.getConnection();
           String sql="DELETE FROM incomeClass WHERE id=?";
           PreparedStatement ptmt=conn.prepareStatement(sql);
           ptmt.setInt(1,incomeClass.getId());
           ptmt.execute();
       }
       /**
        * 功能描述: <br>
        * 〈对收入类别的名称进行修改〉
        *
        * @param incomeClass
        * @return:void
        * @since: 1.0.0
        * @Author:宣佚
        * @Date: 2018/7/14 0014 下午 12:58
        */
       public void updateClass(IncomeClass incomeClass)throws SQLException{
           Connection conn=DBHelper.getConnection();
           String sql="UPDATE incomeClass SET className=? WHERE id=?";
           PreparedStatement ptmt=conn.prepareStatement(sql);
           ptmt.setString(1,incomeClass.getClassName());
           ptmt.setInt(2,incomeClass.getId());
           ptmt.execute();
       }
       /**
        * 功能描述: <br>
        * 〈查询指定的用户和收入类别名称〉
        *
        * @param incomeClass
        * @return:incomeClass
        * @since: 1.0.0
        * @Author:宣佚
        * @Date: 2018/7/14 0014 下午 13:21
        */
       public IncomeClass queryClass(IncomeClass incomeClass)throws SQLException{
           Connection conn=DBHelper.getConnection();
           String sql="SELECT id FROM incomeClass WHERE className=? AND userId=? ";
           PreparedStatement ptmt=conn.prepareStatement(sql);
           ptmt.setString(1,incomeClass.getClassName());
           ptmt.setInt(2,incomeClass.getUserId());
           ResultSet rs=ptmt.executeQuery();
           if(rs.next()){
               IncomeClass incomeClass_query=new IncomeClass();
               incomeClass_query.setId(rs.getInt("id"));
               incomeClass_query.setClassName(incomeClass.getClassName());
               incomeClass_query.setUserId(incomeClass.getUserId());
               return incomeClass_query;
           }
           return null;
       }
       /**
        * 功能描述: <br>
        * 〈查询指定用户的所有收入类别〉
        *
        * @param user
        * @return:arraylist
        * @since: 1.0.0
        * @Author:宣佚
        * @Date: 2018/7/14 0014 下午 13:04
        */
       public ArrayList<IncomeClass>queryAllClass(User user)throws SQLException{
           Connection conn=DBHelper.getConnection();
           String sql="SELECT id,className FROM incomeClass WHERE userId=?";
           PreparedStatement ptmt=conn.prepareStatement(sql);
           ptmt.setInt(1,user.getId());
           ResultSet rs=ptmt.executeQuery();
           ArrayList<IncomeClass>arrayList=new ArrayList<>();
           while (rs.next()){
               IncomeClass incomeClass=new IncomeClass();
               incomeClass.setClassName(rs.getString("className"));
               incomeClass.setId(rs.getInt("id"));
               incomeClass.setUserId(user.getId());
               arrayList.add(incomeClass);
           }
           return arrayList;
       }

       public  static  void main(String args[]){
           IncomeClassControl incomeClassControl=new IncomeClassControl();
           IncomeClass incomeClass=new IncomeClass();
           User user=new User();
           user.setId(1);
           incomeClass.setId(2);
           incomeClass.setUserId(1);
           incomeClass.setClassName("生活费");
           try {
               //incomeClassControl.updateClass(incomeClass);
               /*ArrayList<IncomeClass>arrayList=incomeClassControl.queryAllClass(user);
               for(IncomeClass i:arrayList){
                   System.out.println(i.getId()+"  "+i.getClassName()+" "+i.getUserId());
               }*/
               IncomeClass incomeClass1=incomeClassControl.queryClass(incomeClass);
               System.out.println(incomeClass1.getId()+" "+incomeClass1.getClassName()+" "+incomeClass1.getUserId());
           }catch (SQLException e){
               e.printStackTrace();
           }
       }
}
