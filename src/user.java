/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: user
 * Author:   宣佚
 * Date:     2018/6/16 0016 下午 21:38
 * Description: 用户JavaBean
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户JavaBean〉
 *
 * @author 宣佚
 * @create 2018/6/16 0016
 * @since 1.0.0
 */
public class user {
    private String username;//用户名
    private String password;//用户密码
    private int id;//用户id号

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
