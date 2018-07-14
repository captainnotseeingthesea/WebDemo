/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: Err
 * Author:   宣佚
 * Date:     2018/7/12 0012 上午 10:23
 * Description: 关于错误的提示信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package javaBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈关于错误的提示信息〉
 *
 * @author 宣佚
 * @create 2018/7/12 0012
 * @since 1.0.0
 */
public class Err {
    private int errno;
    private String errmsg;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
