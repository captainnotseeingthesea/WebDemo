/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: IncomeClassService
 * Author:   宣佚
 * Date:     2018/7/15 0015 下午 13:28
 * Description: 收入种类的业务逻辑处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package service.classService;

import control.classControl.IncomeClassControl;
import javaBean.Err;
import javaBean.IncomeClass;
import javaBean.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br> 
 * 〈收入种类的业务逻辑处理〉
 *
 * @author 宣佚
 * @create 2018/7/15 0015
 * @since 1.0.0
 */
public class IncomeClassService {
    /**
     * 功能描述: <br>
     * 〈增加收入种类的逻辑处理〉
     *
     * @param incomeClass
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/15 0015 下午 13:36
     */
    public Err addClass(IncomeClass incomeClass){
        Err err=new Err();
        IncomeClassControl incomeClassControl=new IncomeClassControl();
        String regEx_className="[a-zA-Z\u0391-\uFFE5_]{1,20}";
        Pattern pattern_className=Pattern.compile(regEx_className);
        /*收入种类名称以小写字母，大写字母，下划线等组成，长度在1-20位间*/
        try {
            if(pattern_className.matcher(incomeClass.getClassName()).matches()){
                if(incomeClassControl.queryClass(incomeClass)==null){
                    incomeClassControl.addClass(incomeClass);
                    err.setErrno(0);
                    err.setErrmsg("增加成功");
                }else {
                    err.setErrno(1);
                    err.setErrmsg("该种类已经存在");
                }
            }else {
                    err.setErrno(2);
                    err.setErrmsg("收入种类名称以小写字母，大写字母，下划线等组成，长度在1-20位间");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(3);
            err.setErrmsg("增加异常错误");
        }
        return err;
    }
/**
 * 功能描述: <br>
 * 〈删除收入种类的逻辑处理〉
 *
 * @param incomeClass
 * @return:err
 * @since: 1.0.0
 * @Author:宣佚
 * @Date: 2018/7/15 0015 下午 13:42
 */
    public Err deleteClass(IncomeClass incomeClass){
        Err err=new Err();
        IncomeClassControl incomeClassControl=new IncomeClassControl();
        try {
            incomeClassControl.deleteClass(incomeClass);
            err.setErrno(0);
            err.setErrmsg("删除成功");
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(1);
            err.setErrmsg("删除异常");
        }
        return err;
    }
/**
 * 功能描述: <br>
 * 〈对收入种类进行更新的业务逻处理〉
 *
 * @param incomeClass
 * @return:err
 * @since: 1.0.0
 * @Author:宣佚
 * @Date: 2018/7/15 0015 下午 14:23
 */
    public Err updateClass(IncomeClass incomeClass){
        Err err=new Err();
        IncomeClassControl incomeClassControl=new IncomeClassControl();
        String regEx_className="[a-zA-Z\u0391-\uFFE5_]{1,20}";
        Pattern pattern_className=Pattern.compile(regEx_className);
        /*收入种类名称以小写字母，大写字母，下划线等组成，长度在1-20位间*/
        try {
            if(pattern_className.matcher(incomeClass.getClassName()).matches()){
                if(incomeClassControl.queryClass(incomeClass)==null){
                    incomeClassControl.updateClass(incomeClass);
                    err.setErrno(0);
                    err.setErrmsg("更新成功");
                }else {
                    if(incomeClass.getClassName().equals(incomeClassControl.queryClassById(incomeClass).getClassName())){
                        err.setErrno(1);
                        err.setErrmsg("未进行更改");
                    }else {
                        err.setErrno(2);
                        err.setErrmsg("该种类已存在");
                    }
                }
            }else {
                        err.setErrno(3);
                        err.setErrmsg("收入种类名称以小写字母，大写字母，下划线等组成，长度在1-20位间");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(4);
            err.setErrmsg("更新异常，请重试");
        }
        return err;
    }
/**
 * 功能描述: <br>
 * 〈查询指定id的种类〉
 *
 * @param incomeClass
 * @return:incomeClass
 * @since: 1.0.0
 * @Author:宣佚
 * @Date: 2018/7/16 0016 下午 19:21
 */
    public IncomeClass queryClassById(IncomeClass incomeClass){
        IncomeClass incomeClass_query=new IncomeClass();
        IncomeClassControl incomeClassControl=new IncomeClassControl();
        try {
            incomeClass_query=incomeClassControl.queryClassById(incomeClass);
            return incomeClass_query;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 功能描述: <br>
     * 〈查找指定用户的所有收入种类〉
     *
     * @param user
     * @return:arraylist
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/15 0015 下午 14:26
     */
    public ArrayList<IncomeClass>queryAllClass(User user){
        IncomeClassControl incomeClassControl=new IncomeClassControl();
        try {
            ArrayList<IncomeClass>arrayList=incomeClassControl.queryAllClass(user);
            return arrayList;
        }catch (SQLException e){
            e.printStackTrace();
            return  null;
        }
    }

}
