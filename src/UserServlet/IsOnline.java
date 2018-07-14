package UserServlet;

import javaBean.Err;
import javaBean.User;
import net.sf.json.JSONObject;
import userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "IsOnline")
public class IsOnline extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        UserService userService=new UserService();
        User user=new User();
        Map<String,String>map=new HashMap<String, String>();
        if(session.getAttribute("username")!=null){
            user.setUsername(session.getAttribute("username").toString());
            User queryUser=userService.queryUser(user);
            map.put("errno","0");
            map.put("errmsg","用户在线");
            map.put("username",queryUser.getUsername());
            map.put("signature",queryUser.getSignature());
        }else{
            map.put("errno","1");
            map.put("errmsg","用户不在线");
        }
        System.out.println(JSONObject.fromObject(map));
        out.println(JSONObject.fromObject(map));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
