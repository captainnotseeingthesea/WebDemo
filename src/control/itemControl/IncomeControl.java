/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: IncomeControl
 * Author:   宣佚
 * Date:     2018/7/14 0014 下午 13:39
 * Description: 收入条目的数据库操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package control.itemControl;

import database.DBHelper;
import javaBean.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈收入条目的数据库操作〉
 *
 * @author 宣佚
 * @create 2018/7/14 0014
 * @since 1.0.0
 */
public class IncomeControl {
    /**
     * 功能描述: <br>
     * 〈增加收入条目〉
     *
     * @param income
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 下午 15:57
     */
    public void addIncome(Income income)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="INSERT INTO income (classId,money,incomeTime,userId)VALUES(?,?,?,?)";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1,income.getClassId());
        ptmt.setDouble(2,income.getMoney());
        ptmt.setString(3,income.getIncomeTime());
        ptmt.setInt(4,income.getUserId());
        ptmt.execute();
    }
    /**
     * 功能描述: <br>
     * 〈删除收入条目，置mark=1〉
     *
     * @param income
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 下午 15:59
     */
    public void deleteIncome(Income income)throws SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="UPDATE income SET mark=1 WHERE id=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1,income.getId());
        ptmt.execute();
    }
    /**
     * 功能描述: <br>
     * 〈修改收入条目〉
     *
     * @param income
     * @return:void
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/14 0014 下午 16:03
     */
    public void updateIncome(Income income)throws  SQLException{
        Connection conn=DBHelper.getConnection();
        String sql="UPDATE income SET classId=?,money=?,incomeTime=? WHERE id=?";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setInt(1,income.getClassId());
        ptmt.setDouble(2,income.getMoney());
        ptmt.setString(3,income.getIncomeTime());
        ptmt.setInt(4,income.getId());
        ptmt.execute();
    }

}
