/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: Income
 * Author:   宣佚
 * Date:     2018/7/14 0014 上午 8:13
 * Description: 收入项目
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package javaBean;

import java.sql.Timestamp;

/**
 * 〈一句话功能简述〉<br> 
 * 〈收入项目〉
 *
 * @author 宣佚
 * @create 2018/7/14 0014
 * @since 1.0.0
 */
public class Income {
    private int id;//收入项目的id号
    private int classId;//收入的类别号
    private double money;//收入的金额
    private String incomeTime;//收入金额的日期
    private int userId;//用户的id号
    private int mark;//收入是否有效（有效or无效）

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getIncomeTime() {
        return incomeTime;
    }

    public void setIncomeTime(String incomeTime) {
        this.incomeTime = incomeTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
