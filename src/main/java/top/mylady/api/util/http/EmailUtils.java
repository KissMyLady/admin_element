package top.mylady.api.util.http;

import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: KissMyLady
 * @Date: Created in 2022/5/18 10:37
 * @Description:
 */
public class EmailUtils {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    /**
     * 发送HTML邮件, 注意这里配置的顺序很重要
     * 参考: https://blog.csdn.net/shanshan_blog/article/details/71173582
     */
    public static String send(String fromAddress, String fromPassword, String fromHostName,
                               String sslOnConnect, String sslSmtpPort, String toAddress, String subject, String content) {
        try {
            HtmlEmail htmlEmail = new HtmlEmail();
            htmlEmail.setFrom(fromAddress);// 发送地址
            htmlEmail.setAuthentication(fromAddress, fromPassword);// 密码校验
            htmlEmail.setHostName(fromHostName);// 发送服务器协议
            // SSL
            if ("true".equals(sslOnConnect)) {
                htmlEmail.setSSLOnConnect(true);
                htmlEmail.setSslSmtpPort(sslSmtpPort);
            }
            htmlEmail.addTo(toAddress);		// 接收地址
            htmlEmail.setSubject(subject);  // 标题
            htmlEmail.setMsg(content);	    // 内容
            htmlEmail.setCharset("utf-8");  // 编码
            String send = htmlEmail.send();// 发送
            return send;
        } catch (Exception e) {
            logger.error("邮件发送失败了, 原因是: {}", e.getMessage());
        }
        return "";
    }

    /**
     * 发送普通邮件
     */
    public static String sendSimpleEmail(String fromAddress, String fromPassword, String fromHostName,
                                         String sslOnConnect, String sslSmtpPort, String toAddress, String subject, String content) {
        try {
            SimpleEmail email = new SimpleEmail();

            email.setFrom(fromAddress);// 发送地址
            email.setAuthentication(fromAddress, fromPassword);// 密码校验
            email.setHostName(fromHostName);// 发送服务器协议
            // SSL
            if ("true".equals(sslOnConnect)) {
                email.setSSLOnConnect(true);
                email.setSslSmtpPort(sslSmtpPort);
            }
            email.addTo(toAddress);		// 接收地址
            email.setSubject(subject);  // 标题
            email.setMsg(content);	    // 内容
            email.setCharset("utf-8");  // 编码
            String send = email.send();// 发送
            return send;
        } catch (Exception e) {
            logger.error("邮件发送失败了, 原因是: {}", e.getMessage());
        }
        return "";
    }

    /**
     * 发送邮件测试
     */
    public static void main(String[] args) {
        String fromAddress = "post@zyufl.edu.cn";
        String fromPassword= "Zyufl2020ucp@";
        String fromHostName=  "smtphz.qiye.163.com";
        String sslOnConnect = "true";
        String sslSmtpPort = "465";

        String toAddress = "1373816972@qq.com";
        String subject = "测试邮件发送";
        String content = "Hello World !";

        sendSimpleEmail(fromAddress,fromPassword,fromHostName,sslOnConnect,sslSmtpPort,toAddress,subject,content);
        //EmailUtils.send(fromAddress,fromPassword,fromHostName,sslOnConnect,sslSmtpPort,toAddress,subject,content);
    }
}
