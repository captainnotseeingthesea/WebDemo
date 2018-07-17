package servlet.classServlet;

import javaBean.Err;
import javaBean.PaymentClass;
import javaBean.User;
import net.sf.json.JSONObject;
import service.classService.PaymentClassService;
import service.userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class NewPaymentClass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        Err err=new Err();
        String paymentClassName=request.getParameter("paymentClassName");
        PaymentClassService paymentClassService=new PaymentClassService();
        HttpSession session=request.getSession(true);
        PaymentClass paymentClass=new PaymentClass();
        UserService userService=new UserService();
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登录");
        }else {
            User user=new User();
            user.setUsername(session.getAttribute("username").toString());
            paymentClass.setUserId(userService.queryUser(user).getId());
            paymentClass.setClassName(paymentClassName);
            err=paymentClassService.addClass(paymentClass);
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
