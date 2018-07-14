/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: IncomeClass
 * Author:   宣佚
 * Date:     2018/7/14 0014 上午 8:07
 * Description: 收入类别
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package javaBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈收入类别〉
 *
 * @author 宣佚
 * @create 2018/7/14 0014
 * @since 1.0.0
 */
public class IncomeClass {
    private int id;//收入的种类号
    private String className;//收入的种类名称
    private int userId;//用户的id号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
