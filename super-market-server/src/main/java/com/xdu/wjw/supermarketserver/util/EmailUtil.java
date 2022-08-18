package com.xdu.wjw.supermarketserver.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Class: EmailUtil
 * @Author: Wei Junwei
 * @Time: 2022/8/19 0:05
 * @Description:
 */
public class EmailUtil {

    public final static String MESSAGE_TYPE = "text/html;";
    public final static String CODING_TYPE = "charset=gb2312";

    public static boolean sendEmail(String email, String sendMessage) {
        String from = "2835795351@qq.com";
        String host = "smtp.qq.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");

        // 建立会话，使用密码验证机制
        try {
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
                    return new PasswordAuthentication("2835795351@qq.com", "wjtxwqponirvddgc");
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email));
            message.setSubject("验证码");

            message.setContent(EmailTemplate.genHtmlAuthMessage(email, sendMessage), MESSAGE_TYPE + CODING_TYPE);
            Transport.send(message);
        } catch (MessagingException e) {
            LogUtil.getCommonLogger(EmailUtil.class).error("[Send Email Error] to {}", email, e);
            return false;
        }
        return true;
    }
}
