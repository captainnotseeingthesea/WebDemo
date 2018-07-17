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

public class DeleteItems extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);
        Err err=new Err();
        IncomeService incomeService=new IncomeService();
        PaymentService paymentService=new PaymentService();
        int id=Integer.parseInt(request.getParameter("id"));
        int query=Integer.parseInt(request.getParameter("query"));
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登陆");
        }else {
            if(query==0){//删除收入条目
                Income income=new Income();
                income.setId(id);
                err=incomeService.deleteIncome(income);
            }else {//删除支出条目
                Payment payment=new Payment();
                payment.setId(id);
                err=paymentService.deletePayment(payment);
            }
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
