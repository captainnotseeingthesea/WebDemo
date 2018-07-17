package servlet.itemServlet;

import comparator.ItemComparator;
import javaBean.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.classService.IncomeClassService;
import service.classService.PaymentClassService;
import service.itemService.IncomeService;
import service.itemService.PaymentService;
import service.userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class DisplayItems extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);
        PaymentService paymentService=new PaymentService();
        IncomeService incomeService=new IncomeService();
        UserService userService=new UserService();
        IncomeClassService incomeClassService=new IncomeClassService();
        PaymentClassService paymentClassService=new PaymentClassService();
        int query=Integer.parseInt(request.getParameter("query"));
        Err err=new Err();
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登陆");
            out.println(JSONObject.fromObject(err));
        }else {
            User user=new User();
            user.setUsername(session.getAttribute("username").toString());
            ArrayList<Payment>arrayList_payment=paymentService.displayAllItems(userService.queryUser(user));
            ArrayList<Income>arrayList_income=incomeService.displayAllItems(userService.queryUser(user));
            ArrayList<Item>arrayList_item=new ArrayList<>();
            switch (query){
                case 0:/*查询全部的收入和支出条目且按照日期进行排序*/
                    if(arrayList_income!=null){
                        if(arrayList_payment!=null){
                            for(Payment payment:arrayList_payment){
                                Item item=new Item();
                                PaymentClass paymentClass=new PaymentClass();
                                paymentClass.setId(payment.getClassId());
                                item.setId(payment.getId());
                                item.setMoney(payment.getMoney());
                                item.setTime(payment.getPayTime());
                                item.setClassId(payment.getClassId());
                                item.setMark(0);
                                item.setQuery(1);
                                item.setUserId(payment.getUserId());
                                item.setClassName(paymentClassService.queryClassById(paymentClass).getClassName());
                                arrayList_item.add(item);
                            }
                            for(Income income:arrayList_income){
                                Item item=new Item();
                                IncomeClass incomeClass=new IncomeClass();
                                incomeClass.setId(income.getClassId());
                                item.setId(income.getId());
                                item.setMoney(income.getMoney());
                                item.setTime(income.getIncomeTime());
                                item.setClassId(income.getClassId());
                                item.setClassName(incomeClassService.queryClassById(incomeClass).getClassName());
                                item.setMark(0);
                                item.setQuery(0);
                                item.setUserId(income.getUserId());
                                arrayList_item.add(item);
                            }
                            Collections.sort(arrayList_item,new ItemComparator());
                            out.println(JSONArray.fromObject(arrayList_item));
                        }else {
                            err.setErrno(1);
                            err.setErrmsg("支出条目查询异常");
                            out.println(JSONObject.fromObject(err));
                        }
                    }else {
                            err.setErrno(2);
                            err.setErrmsg("收入条目查询异常");
                            out.println(JSONObject.fromObject(err));
                    }
                    break;
                case 1:/*查询全部的收入条目*/
                    if(arrayList_income!=null){
                        for(Income income:arrayList_income){
                            Item item=new Item();
                            IncomeClass incomeClass=new IncomeClass();
                            incomeClass.setId(income.getClassId());
                            item.setId(income.getId());
                            item.setMoney(income.getMoney());
                            item.setTime(income.getIncomeTime());
                            item.setClassId(income.getClassId());
                            item.setMark(0);
                            item.setQuery(0);
                            item.setUserId(income.getUserId());
                            item.setClassName(incomeClassService.queryClassById(incomeClass).getClassName());
                            arrayList_item.add(item);
                        }
                        Collections.sort(arrayList_item,new ItemComparator());
                        out.println(JSONArray.fromObject(arrayList_item));
                    }else {
                        err.setErrno(2);
                        err.setErrmsg("收入条目查询异常");
                        out.println(JSONObject.fromObject(err));
                    }
                    break;
                case 2:/*查询全部的支出条目*/
                    if(arrayList_payment!=null){
                        for(Payment payment:arrayList_payment){
                            Item item=new Item();
                            PaymentClass paymentClass=new PaymentClass();
                            paymentClass.setId(payment.getClassId());
                            item.setId(payment.getId());
                            item.setMoney(payment.getMoney());
                            item.setTime(payment.getPayTime());
                            item.setClassId(payment.getClassId());
                            item.setMark(0);
                            item.setQuery(1);
                            item.setUserId(payment.getUserId());
                            item.setClassName(paymentClassService.queryClassById(paymentClass).getClassName());
                            arrayList_item.add(item);
                        }
                        Collections.sort(arrayList_item,new ItemComparator());
                        out.println(JSONArray.fromObject(arrayList_item));
                    }else {
                        err.setErrno(1);
                        err.setErrmsg("支出条目查询异常");
                        out.println(JSONObject.fromObject(err));
                    }
                    break;
                default:/*错误的查询条件*/
                    err.setErrno(3);
                    err.setErrmsg("错误的查询条件");
                    out.println(JSONObject.fromObject(err));
                    break;
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}