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
import java.util.HashMap;
import java.util.Map;

public class UserReg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        HttpSession session=request.getSession(true);
        User user=new User();
        UserService userService=new UserService();
        PrintWriter out=response.getWriter();
        String username=request.getParameter("username");
        String password_reg=request.getParameter("password_reg");
        String password_confirm=request.getParameter("password_confirm");
        Map<String,String>map=new HashMap<String, String>();
        user.setUsername(username);
        user.setPassword(password_reg);
        Err err=userService.userReg(user,password_confirm);
        map.put("errno",""+err.getErrno());
        map.put("errmsg",err.getErrmsg());
        if(err.getErrno()==0){
            session.setAttribute("username",username);
            map.put("username",user.getUsername());
            map.put("signature",userService.queryUser(user).getSignature());
        }
        out.println(JSONObject.fromObject(map));
        System.out.println(JSONObject.fromObject(map));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
