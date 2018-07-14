/**
 * Copyright (C), 2015-2018, 华电408有限公司
 * FileName: EncryptPassword
 * Author:   宣佚
 * Date:     2018/7/12 0012 上午 9:45
 * Description: 对密码进行加密处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package encrypt;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈对密码进行加密处理〉
 *
 * @author 宣佚
 * @create 2018/7/12 0012
 * @since 1.0.0
 */
public class EncryptPassword {
    public static String encryptPassword(String password){
        String sha1=DigestUtils.sha1Hex(password);
        byte[] base64= Base64.encodeBase64(sha1.getBytes(),true);
        return DigestUtils.md5Hex(base64);
    }
    public static void main(String args[]){
        String password="lxy991120";
        String new_password=encryptPassword(password);
        System.out.println(new_password);
    }
}
