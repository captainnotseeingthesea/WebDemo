/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: Payment
 * Author:   宣佚
 * Date:     2018/7/14 0014 上午 8:31
 * Description: 支出项目
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package javaBean;

import java.sql.Timestamp;

/**
 * 〈一句话功能简述〉<br> 
 * 〈支出项目〉
 *
 * @author 宣佚
 * @create 2018/7/14 0014
 * @since 1.0.0
 */
public class Payment {
    private int id;//支出项目的id号
    private int classId;//支出种类的id号
    private double money;//支出金额
    private String payTime;//支出金额的日期
    private int userId;//用户的id号
    private int mark;//支出是否有效（有效or无效）

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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
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