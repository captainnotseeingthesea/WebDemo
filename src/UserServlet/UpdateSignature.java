package UserServlet;

import javaBean.Err;
import javaBean.User;
import net.sf.json.JSONObject;
import userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateSignature extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);
        UserService userService=new UserService();
        String signature=request.getParameter("signature");
        User user=new User();
        Err err=new Err();
        if(session.getAttribute("username")==null){
            err.setErrno(3);
            err.setErrmsg("会话超时，请重新登录");
        }else {
            user.setUsername(session.getAttribute("username").toString());
            user.setSignature(signature);
            err=userService.updateSignature(user);
        }
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
