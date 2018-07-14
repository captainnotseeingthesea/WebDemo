package servlet.UserServlet;

import javaBean.Err;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserExit")
public class UserExit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);
        Err err=new Err();
        if(session.getAttribute("username")!=null){
            session.removeAttribute("username");
            err.setErrno(0);
            err.setErrmsg("成功退出");
        }else{
            err.setErrno(1);
            err.setErrmsg("用户状态异常");
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
