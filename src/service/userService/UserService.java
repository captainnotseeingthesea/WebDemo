/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: UserService
 * Author:   宣佚
 * Date:     2018/7/12 0012 上午 10:21
 * Description: 处理用户登录，注册等方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package service.userService;

import encrypt.EncryptPassword;
import javaBean.Err;
import javaBean.User;
import control.userControl.UserControl;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br> 
 * 〈处理用户登录，注册等方法〉
 *
 * @author 宣佚
 * @create 2018/7/12 0012
 * @since 1.0.0
 */
public class UserService {

    /**
     * 功能描述: <br>
     * 〈对用户的注册信息进行判别并且完成注册〉
     *err的对应关系：
     * 0    成功注册
     * 1    用户名已经存在
     * 2    两次密码不一致
     * 3    密码的长度必须在6-16位间
     * 4    用户名以字母/下划线开头+数字/字母/下划线，位数在1-10位间
     * 5    异常错误
     * @param user,password_confirm
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/12 0012 上午 10:59
     */
    public Err userReg(User user,String password_confirm){
        UserControl userControl=new UserControl();
        Err err=new Err();
        /*用户名以字母/下划线开头+数字/字母/下划线，位数在1-10位间*/
        String regEx_username="^[a-zA-Z\u0391-\uFFE5_][A-Za-z0-9_\u0391-\uFFE5-]{0,9}";
        /*密码的长度必须在6-16位间*/
        String regEx_password=".{6,16}";
        Pattern pattern_username=Pattern.compile(regEx_username);
        Pattern pattern_password=Pattern.compile(regEx_password);
        try {
            if(pattern_username.matcher(user.getUsername()).matches()){
                if(pattern_password.matcher(user.getPassword()).matches()){
                    if(user.getPassword().equals(password_confirm)){
                        if(userControl.queryUser(user)==null){
                            err.setErrno(0);
                            err.setErrmsg("用户注册成功");
                            userControl.addUser(user);
                        }else{//该用户名已经存在了
                            err.setErrno(1);
                            err.setErrmsg("该用户名已经存在了");
                        }
                    }else{//两次密码不一致
                            err.setErrno(2);
                            err.setErrmsg("两次密码不一致");
                    }
                }else{//密码的长度必须在6-16位间
                            err.setErrno(3);
                            err.setErrmsg("密码的长度必须在6-16位间");
                }
            }else{//用户名以汉字/字母/下划线开头+汉字/数字/字母/下划线，位数在1-10位间
                            err.setErrno(4);
                            err.setErrmsg("用户名以字母/下划线开头+数字/字母/下划线，位数在1-10位间");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(5);
            err.setErrmsg("异常错误");
        }
        return err;
    }
/**
 * 功能描述: <br>
 * 〈用户登录的判别〉
 * err的对应关系：
 * 0    用户登录成功
 * 1    密码错误
 * 2    用户名不存在
 * 3    密码的长度必须在6-16位间
 * 4    用户名以字母/下划线开头+数字/字母/下划线，位数在1-10位间
 * 5    异常错误
 * @param user
 * @return:err
 * @since: 1.0.0
 * @Author:宣佚
 * @Date: 2018/7/12 0012 上午 11:28
 */
    public  Err userLogin(User user){
        Err err=new Err();
        UserControl userControl=new UserControl();
        /*用户名以字母/下划线开头+数字/字母/下划线，位数在1-10位间*/
        String regEx_username="^[a-zA-Z\u0391-\uFFE5_][A-Za-z0-9_\u0391-\uFFE5-]{0,9}";
        /*密码的长度必须在6-16位间*/
        String regEx_password=".{6,16}";
        Pattern pattern_username=Pattern.compile(regEx_username);
        Pattern pattern_password=Pattern.compile(regEx_password);
        try {
            if(pattern_username.matcher(user.getUsername()).matches()){
                if(pattern_password.matcher(user.getPassword()).matches()){
                    String encrypt_password=EncryptPassword.encryptPassword(user.getPassword());
                    if(userControl.queryUser(user)!=null){
                        if(userControl.queryUser(user).getPassword().equals(encrypt_password)){
                            err.setErrno(0);
                            err.setErrmsg("登录成功");
                        }else {
                            err.setErrno(1);
                            err.setErrmsg("密码错误");
                        }
                    }else{
                            err.setErrno(2);
                            err.setErrmsg("用户名不存在");
                    }
                }else{
                            err.setErrno(3);
                            err.setErrmsg("密码的长度必须在6-16位间");
                }
            }else{
                            err.setErrno(4);
                            err.setErrmsg("用户名以字母/下划线开头+数字/字母/下划线，位数在1-10位间");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(5);
            err.setErrmsg("异常错误");
        }
        return  err;
    }
    /**
     * 功能描述: <br>
     * 〈查询指定的用户信息〉
     *
     * @param user
     * @return:user
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/13 0013 上午 11:46
     */
    public User queryUser(User user){
        UserControl userControl=new UserControl();
        try {
            return userControl.queryUser(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Err updateSignature(User user){
        UserControl userControl=new UserControl();
        /*个性签名的位数在1-50位间,不可都为空格*/
        String regEx=".{1,50}";
        Pattern pattern_sinature=Pattern.compile(regEx);
        Err err=new Err();
        try {
            if(pattern_sinature.matcher(user.getSignature()).matches()){
                userControl.updateSignature(user);
                err.setErrno(0);
                err.setErrmsg("个性签名修改成功");
            }else {
                err.setErrno(1);
                err.setErrmsg("个性签名的位数在1-50位间");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(2);
            err.setErrmsg("修改异常错误");
        }
       return err;
    }
    /**
     * 功能描述: <br>
     * 〈修改用户的密码〉
     *
     * @param user,password_old
     * @return:err
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/13 0013 上午 11:44
     */
    public Err updatePassword(User user,String password_old){
        Err err=new Err();
        UserControl userControl=new UserControl();
        /*用户名以字母/下划线开头+数字/字母/下划线，位数在1-10位间*/
        String regEx_username="^[a-zA-Z\u0391-\uFFE5_][A-Za-z0-9_\u0391-\uFFE5-]{0,9}";
        /*密码的长度必须在6-16位间*/
        String regEx_password=".{6,16}";
        Pattern pattern_username=Pattern.compile(regEx_username);
        Pattern pattern_password=Pattern.compile(regEx_password);
        try {
            if(pattern_username.matcher(user.getUsername()).matches()){
                if(pattern_password.matcher(password_old).matches()){
                    if(pattern_password.matcher(user.getPassword()).matches()){
                        if(userControl.queryUser(user)!=null){
                            if(userControl.queryUser(user).getPassword().equals(EncryptPassword.encryptPassword(password_old))){
                                userControl.updatePassword(user);
                                err.setErrno(0);
                                err.setErrmsg("密码修改成功");
                            }else{
                                err.setErrno(1);
                                err.setErrmsg("原密码错误");
                            }
                        }else{
                                err.setErrno(2);
                                err.setErrmsg("用户名不存在");
                        }
                    }else{
                                err.setErrno(3);
                                err.setErrmsg("新密码的长度必须在6-16位间");
                    }
                }else{
                                err.setErrno(4);
                                err.setErrmsg("原始密码的长度必须在6-16位间");
                }
            }else{
                                err.setErrno(5);
                                err.setErrmsg("用户名以字母/下划线开头+数字/字母/下划线，位数在1-10位间");
            }
        }catch (SQLException e){
            e.printStackTrace();
            err.setErrno(6);
            err.setErrmsg("异常错误");
        }

        return err;
    }

    /**
     * 功能描述: <br>
     * 〈测试〉
     *
     * @param args
     * @return:null
     * @since: 1.0.0
     * @Author:宣佚
     * @Date: 2018/7/12 0012 上午 11:05
     */
    public static void main(String args[]){
        UserService userService=new UserService();
        User user=new User();
        user.setUsername("李东初");
        user.setPassword("ldc123456");
        user.setSignature("");
        //Err err=service.userService.userReg(user,"ldc123456");
        //Err err=service.userService.userLogin(user);
        Err err=userService.updateSignature(user);
        System.out.println(err.getErrno()+" "+err.getErrmsg());
    }
}
