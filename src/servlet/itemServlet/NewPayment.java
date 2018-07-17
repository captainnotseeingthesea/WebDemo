package servlet.itemServlet;

import javaBean.Err;
import javaBean.Payment;
import javaBean.User;
import net.sf.json.JSONObject;
import service.itemService.PaymentService;
import service.userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class NewPayment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);
        int classId=Integer.parseInt(request.getParameter("classId"));
        String time=request.getParameter("time");
        double money=Double.parseDouble(request.getParameter("money"));
        UserService userService=new UserService();
        PaymentService paymentService=new PaymentService();
        Err err=new Err();
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登陆");
        }else {
            Payment payment=new Payment();
            User user=new User();
            user.setUsername(session.getAttribute("username").toString());
            payment.setClassId(classId);
            payment.setPayTime(time);
            payment.setMoney(money);
            payment.setUserId(userService.queryUser(user).getId());
            err=paymentService.addPayment(payment);
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
