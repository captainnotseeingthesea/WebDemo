/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: IncomeService
 * Author:   宣佚
 * Date:     2018/7/16 0016 上午 11:39
 * Description: 对收入条目业务的逻辑处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package service.itemService;

import control.itemControl.IncomeControl;
import javaBean.Err;
import javaBean.Income;
import javaBean.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈对收入条目业务的逻辑处理〉
 *
 * @author 宣佚
 * @create 2018/7/16 0016
 * @since 1.0.0
 */
public class IncomeService {
    /**
     * 功能描述: <br>
     * 〈对收入条目增加的业务逻辑处理〉
     *
     * @param income
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/16 0016 上午 11:56
     */
    public Err addIncome(Income income){
        Err err=new Err();
        IncomeControl incomeControl=new IncomeControl();
        try {
            if(income.getClassId()+""!=null){
                if(income.getIncomeTime()!=null){
                    if(income.getMoney()+""!=null){
                        incomeControl.addIncome(income);
                        err.setErrno(0);
                        err.setErrmsg("增加成功");
                    }else {
                        err.setErrno(1);
                        err.setErrmsg("收入金额不可为空");
                    }
                }else {
                        err.setErrno(2);
                        err.setErrmsg("收入日期不可为空");
                }
            }else {
                        err.setErrno(3);
                        err.setErrmsg("收入种类不可为空");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(4);
            err.setErrmsg("增加异常错误");
        }
        return err;
    }
    /**
     * 功能描述: <br>
     * 〈查询指定用户所有的收入条目的业务逻辑处理〉
     *
     * @param user
     * @return:arrayList
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/17 0017 上午 8:26
     */
    public ArrayList<Income> displayAllItems(User user){
        IncomeControl incomeControl=new IncomeControl();
        try {
            ArrayList<Income>arrayList=incomeControl.queryAllItems(user);
            return arrayList;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 功能描述: <br>
     * 〈更新收入条目的业务逻辑处理〉
     *
     * @param income
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/17 0017 上午 8:45
     */
    public Err updateIncome(Income income){
        IncomeControl incomeControl=new IncomeControl();
        Err err=new Err();
        try {
            if(income.getClassId()+""!=null){
                if(income.getIncomeTime()!=null){
                    if(income.getMoney()+""!=null){
                        incomeControl.updateIncome(income);
                        err.setErrno(0);
                        err.setErrmsg("更新成功");
                    }else {
                        err.setErrno(1);
                        err.setErrmsg("收入金额不可为空");
                    }
                }else {
                        err.setErrno(2);
                        err.setErrmsg("收入日期不可为空");
                }
            }else {
                        err.setErrno(3);
                        err.setErrmsg("收入种类不可为空");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(4);
            err.setErrmsg("更新异常错误");
        }
        return err;
    }

    public Err deleteIncome(Income income){
        Err err=new Err();
        IncomeControl incomeControl=new IncomeControl();
        try {
            incomeControl.deleteIncome(income);
            err.setErrno(0);
            err.setErrmsg("删除成功");
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(1);
            err.setErrmsg("删除异常错误");
        }
        return err;
    }
}
