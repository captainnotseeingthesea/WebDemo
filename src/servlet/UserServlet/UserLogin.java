package servlet.UserServlet;

import javaBean.Err;
import javaBean.User;
import net.sf.json.JSONObject;
import service.userService.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserLogin extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession(true);
        User user=new User();
        UserService userService=new UserService();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        user.setPassword(password);
        user.setUsername(username);
        Err err=userService.userLogin(user);
        Map<String,String>map=new HashMap<String, String>();
        if(err.getErrno()==0){
            session.setAttribute("username",user.getUsername());
            map.put("signature",userService.queryUser(user).getSignature());
        }
        map.put("errno",""+err.getErrno());
        map.put("errmsg",err.getErrmsg());
        out.println(JSONObject.fromObject(map));
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
