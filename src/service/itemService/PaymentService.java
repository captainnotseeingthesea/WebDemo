/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: PaymentService
 * Author:   宣佚
 * Date:     2018/7/16 0016 上午 11:39
 * Description: 对支出条目业务的逻辑处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package service.itemService;

import control.itemControl.PaymentControl;
import javaBean.Err;
import javaBean.Payment;
import javaBean.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈对支出条目业务的逻辑处理〉
 *
 * @author 宣佚
 * @create 2018/7/16 0016
 * @since 1.0.0
 */
public class PaymentService {
    /**
     * 功能描述: <br>
     * 〈对支出条目增加的业务逻辑处理〉
     *
     * @param payment
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/16 0016 上午 11:55
     */
    public Err addPayment(Payment payment){
        Err err=new Err();
        PaymentControl paymentControl=new PaymentControl();
        try {
            if(payment.getClassId()+""!=null){
                if(payment.getPayTime()!=null){
                    if(payment.getMoney()+""!=null){
                        paymentControl.addPayment(payment);
                        err.setErrno(0);
                        err.setErrmsg("增加成功");
                    }else {
                        err.setErrno(1);
                        err.setErrmsg("支出价格不可为空");
                    }
                }else {
                        err.setErrno(2);
                        err.setErrmsg("支付日期不可为空");
                }
            }else {
                        err.setErrno(3);
                        err.setErrmsg("支付种类不可为空");
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
     * 〈查询指定用户所有的支出条目的业务逻辑处理〉
     *
     * @param user
     * @return:arraylist
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/16 0016 下午 15:15
     */
    public ArrayList<Payment> displayAllItems(User user){
        PaymentControl paymentControl=new PaymentControl();
        try {
            ArrayList<Payment>arrayList=paymentControl.queryAllItems(user);
            return arrayList;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 功能描述: <br>
     * 〈更新支出条目的业务逻辑处理〉
     *
     * @param payment
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/17 0017 上午 8:50
     */
    public Err updatePayment(Payment payment){
        PaymentControl paymentControl=new PaymentControl();
        Err err=new Err();
        try {
            if(payment.getClassId()+""!=null){
                if(payment.getPayTime()!=null){
                    if(payment.getMoney()+""!=null){
                        paymentControl.updatePayment(payment);
                        err.setErrno(0);
                        err.setErrmsg("更新成功");
                    }else {
                        err.setErrno(1);
                        err.setErrmsg("支出金额不可为空");
                    }
                }else {
                        err.setErrno(2);
                        err.setErrmsg("支出日期不可为空");
                }
            }else {
                        err.setErrno(3);
                        err.setErrmsg("支出种类不可为空");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(4);
            err.setErrmsg("更新异常错误");
        }
        return err;
    }
    /**
     * 功能描述: <br>
     * 〈删除支出条目的业务逻辑处理〉
     *
     * @param payment
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/17 0017 上午 10:58
     */
    public Err deletePayment(Payment payment){
        PaymentControl paymentControl=new PaymentControl();
        Err err=new Err();
        try {
            paymentControl.deletePayment(payment);
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
