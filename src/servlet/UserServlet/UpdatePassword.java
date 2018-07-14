package servlet.UserServlet;

import javaBean.Err;
import javaBean.User;
import net.sf.json.JSONObject;
import service.userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdatePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        UserService userService=new UserService();
        User user=new User();
        String username=request.getParameter("username");
        String password_old=request.getParameter("password_old");
        String password_new=request.getParameter("password_new");
        user.setUsername(username);
        user.setPassword(password_new);
        Err err=userService.updatePassword(user,password_old);
        out.println(JSONObject.fromObject(err));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
