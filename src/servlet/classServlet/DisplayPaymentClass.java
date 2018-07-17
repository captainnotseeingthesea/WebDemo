package servlet.classServlet;

import javaBean.Err;
import javaBean.PaymentClass;
import javaBean.User;
import net.sf.json.JSONArray;
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
import java.util.ArrayList;

public class DisplayPaymentClass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        PaymentClassService paymentClassService=new PaymentClassService();
        UserService userService=new UserService();
        HttpSession session=request.getSession(true);
        Err err=new Err();
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登陆");
            out.println(JSONObject.fromObject(err));
        }else {
            User user=new User();
            user.setUsername(session.getAttribute("username").toString());
            ArrayList<PaymentClass>arrayList=paymentClassService.queryAllClass(userService.queryUser(user));
            if(arrayList==null){//查询异常
                err.setErrno(2);
                err.setErrmsg("查询异常错误");
            }else{
                out.println(JSONArray.fromObject(arrayList));
                System.out.println(JSONArray.fromObject(arrayList));
            }
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
