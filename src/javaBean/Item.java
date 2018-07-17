/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: Item
 * Author:   宣佚
 * Date:     2018/7/16 0016 下午 16:08
 * Description: 支出、收入的条目
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package javaBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈支出、收入的条目〉
 *
 * @author 宣佚
 * @create 2018/7/16 0016
 * @since 1.0.0
 */
public class Item {
    private int id;//条目的id号
    private int classId;//条目的类别
    private double money;//条目的金额
    private String time;//条目生成的时间
    private String className;//条目种类名称
    private int userId;//条目的用户id号
    private int query;//收入or支出---0为收入，1为支出
    private int mark;//条目是否存在---0为存在，1为不存在

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuery() {
        return query;
    }

    public void setQuery(int query) {
        this.query = query;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }


}
