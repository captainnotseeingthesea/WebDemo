package servlet.classServlet;

import javaBean.Err;
import javaBean.PaymentClass;
import net.sf.json.JSONObject;
import service.classService.PaymentClassService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class DeletePaymentClass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        Err err=new Err();
        HttpSession session=request.getSession(true);
        PaymentClassService paymentClassService=new PaymentClassService();
        PaymentClass paymentClass=new PaymentClass();
        System.out.println(request.getParameter("id"));
        int id=Integer.parseInt(request.getParameter("id"));
        paymentClass.setId(id);
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登录");
        }else{
            err=paymentClassService.deleteClass(paymentClass);
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
