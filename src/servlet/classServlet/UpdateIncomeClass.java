package servlet.classServlet;

import javaBean.Err;
import javaBean.IncomeClass;
import javaBean.User;
import net.sf.json.JSONObject;
import service.classService.IncomeClassService;
import service.userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateIncomeClass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        Err err=new Err();
        HttpSession session=request.getSession(true);
        IncomeClass incomeClass=new IncomeClass();
        IncomeClassService incomeClassService=new IncomeClassService();
        String incomeClassName=request.getParameter("incomeClassName");
        int id=Integer.parseInt(request.getParameter("id"));
        UserService userService=new UserService();
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登陆");
        }else {
            User user=new User();
            user.setUsername(session.getAttribute("username").toString());
            incomeClass.setId(id);
            incomeClass.setClassName(incomeClassName);
            incomeClass.setUserId(userService.queryUser(user).getId());
            err=incomeClassService.updateClass(incomeClass);
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
