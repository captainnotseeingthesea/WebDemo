/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: ItemComparator
 * Author:   宣佚
 * Date:     2018/7/16 0016 下午 16:17
 * Description: Item条目的比较
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package comparator;

import javaBean.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Item条目的比较〉
 *
 * @author 宣佚
 * @create 2018/7/16 0016
 * @since 1.0.0
 */
public class ItemComparator implements Comparator<Item> {
    public int compare(Item l,Item r){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日hh时");
        try {
            Date date_l=simpleDateFormat.parse(l.getTime());
            Date date_r=simpleDateFormat.parse(r.getTime());
            if(date_l.getTime()>date_r.getTime()){
                return -1;
            }else {
                return 1;
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String args[]){
        Item item1=new Item();
        item1.setTime("2016年12月27日01时");
        item1.setMoney(12);
        Item item2=new Item();
        item2.setTime("2018年12月27日10时");
        item2.setMoney(13);
        Item item3=new Item();
        item3.setTime("2017年12月20日10时");
        item3.setMoney(14);
        ArrayList<Item>arrayList=new ArrayList<>();
        arrayList.add(item1);
        arrayList.add(item2);
        arrayList.add(item3);
        System.out.println(new ItemComparator().compare(item2,item1));
        for(Item item:arrayList){
            System.out.println(item.getTime()+" "+item.getMoney());
        }
        Collections.sort(arrayList,new ItemComparator());
        for(Item item:arrayList){
            System.out.println(item.getTime()+" "+item.getMoney());
        }
    }
}
