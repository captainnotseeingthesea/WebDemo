package servlet.classServlet;

import javaBean.Err;
import javaBean.IncomeClass;
import net.sf.json.JSONObject;
import service.classService.IncomeClassService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteIncomeClass extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        IncomeClassService incomeClassService=new IncomeClassService();
        Err err=new Err();
        int id=Integer.parseInt(request.getParameter("id"));
        IncomeClass incomeClass=new IncomeClass();
        incomeClass.setId(id);
        HttpSession session=request.getSession(true);
        if(session.getAttribute("username")==null){
            err.setErrno(10);
            err.setErrmsg("会话超时，请重新登录");
        }else {
            err=incomeClassService.deleteClass(incomeClass);
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
