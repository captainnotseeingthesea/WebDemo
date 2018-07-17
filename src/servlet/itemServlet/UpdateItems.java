package servlet.itemServlet;

import javaBean.Err;
import javaBean.Income;
import javaBean.Payment;
import net.sf.json.JSONObject;
import service.itemService.IncomeService;
import service.itemService.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateItems extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);
        int query=Integer.parseInt(request.getParameter("query"));
        int id=Integer.parseInt(request.getParameter("id"));
        int classId=Integer.parseInt(request.getParameter("classId"));
        String time=request.getParameter("time");
        double money=Double.parseDouble(request.getParameter("money"));
        IncomeService incomeService=new IncomeService();
        PaymentService paymentService=new PaymentService();
        Err err=new Err();
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登陆");
        }else{
            if(query==0){//更新收入条目
                Income income=new Income();
                income.setId(id);
                income.setClassId(classId);
                income.setIncomeTime(time);
                income.setMoney(money);
                err=incomeService.updateIncome(income);
            }else {//更新支出条目
                Payment payment=new Payment();
                payment.setId(id);
                payment.setClassId(classId);
                payment.setPayTime(time);
                payment.setMoney(money);
                err=paymentService.updatePayment(payment);
            }
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
