/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: PaymentClassService
 * Author:   宣佚
 * Date:     2018/7/15 0015 上午 9:40
 * Description: 支出种类的业务处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package service.classService;

import control.classControl.PaymentClassControl;
import javaBean.Err;
import javaBean.PaymentClass;
import javaBean.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br> 
 * 〈支出种类的业务处理〉
 *
 * @author 宣佚
 * @create 2018/7/15 0015
 * @since 1.0.0
 */
public class PaymentClassService {
    /**
     * 功能描述: <br>
     * 〈对增加支出种类的业务逻辑处理〉
     *
     * @param paymentClass
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/15 0015 上午 10:00
     */
    public Err addClass(PaymentClass paymentClass){
        PaymentClassControl paymentClassControl=new PaymentClassControl();
        String regEx_className="[a-zA-Z\u0391-\uFFE5_]{1,20}";
        Pattern pattern_className=Pattern.compile(regEx_className);
        /*支出种类名称以小写字母，大写字母，下划线等组成，长度在1-20位间*/
        Err err=new Err();
        try {
            if(pattern_className.matcher(paymentClass.getClassName()).matches()){
                if(paymentClassControl.queryClass(paymentClass)==null){
                    paymentClassControl.addClass(paymentClass);
                    err.setErrno(0);
                    err.setErrmsg("增加成功");
                }else {
                    err.setErrno(1);
                    err.setErrmsg("该种类已存在");
                }
            }else{
                    err.setErrno(2);
                    err.setErrmsg("支出种类名称以小写字母，大写字母，下划线等组成，长度在1-20位间");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(3);
            err.setErrmsg("增加异常");
        }
        return err;
    }
    /**
     * 功能描述: <br>
     * 〈对删除支出种类的业务逻辑处理〉
     *
     * @param paymentClass
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/15 0015 上午 10:04
     */
    public Err deleteClass(PaymentClass paymentClass){
        PaymentClassControl paymentClassControl=new PaymentClassControl();
        Err err=new Err();
        try {
            paymentClassControl.deleteClass(paymentClass);
            err.setErrno(0);
            err.setErrmsg("删除成功");
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(1);
            err.setErrmsg("删除异常，请检查删除的种类");
        }
        return err;
    }
    /**
     * 功能描述: <br>
     * 〈对支出种类更新的业务逻辑处理〉
     *
     * @param paymentClass
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/15 0015 上午 10:20
     */
    public Err updateClass(PaymentClass paymentClass){
        PaymentClassControl paymentClassControl=new PaymentClassControl();
        Err err=new Err();
        String regEx_className="[a-zA-Z\u0391-\uFFE5_]{1,20}";
        Pattern pattern_className=Pattern.compile(regEx_className);
        /*支出种类名称以小写字母，大写字母，下划线等组成，长度在1-20位间*/
        try {
            if(pattern_className.matcher(paymentClass.getClassName()).matches()){
                if(paymentClassControl.queryClass(paymentClass)==null){
                    paymentClassControl.updateClass(paymentClass);
                    err.setErrno(0);
                    err.setErrmsg("更新成功");
                }else{
                    if(paymentClass.getClassName().equals(paymentClassControl.queryClassById(paymentClass).getClassName())){
                        err.setErrno(1);
                        err.setErrmsg("未进行更改");
                    }else{
                        err.setErrno(2);
                        err.setErrmsg("该种类已存在");
                    }
                }
            }else{
                err.setErrno(3);
                err.setErrmsg("支出种类名称以小写字母，大写字母，下划线等组成，长度在1-20位间");
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
     * 〈查询指定id的支出种类〉
     *
     * @param paymentClass
     * @return:paymentClass
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/16 0016 下午 19:27
     */
    public PaymentClass queryClassById(PaymentClass paymentClass){
        PaymentClassControl paymentClassControl=new PaymentClassControl();
        PaymentClass paymentClass_query=new PaymentClass();
        try {
            paymentClass_query=paymentClassControl.queryClassById(paymentClass);
            return paymentClass_query;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 功能描述: <br>
     * 〈查询指定用户的所有支出种类〉
     *
     * @param user
     * @return:arraylist
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/15 0015 上午 10:28
     */
    public ArrayList<PaymentClass> queryAllClass(User user){
        PaymentClassControl paymentClassControl=new PaymentClassControl();
        try {
            ArrayList<PaymentClass>arrayList=paymentClassControl.queryAllClass(user);
            return arrayList;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
