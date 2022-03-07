package com.group5.hawadeeleasemanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    //定义邮件发送器
    @Autowired
    private JavaMailSender mailSender;

    //定义邮件发送者
    @Value("${spring.mail.username}")
    private String from;

    /**
     * @param to 接收者
     * @param subject 主题
     * @param content 内容
     * @Value注解读取配置文件中同名的配置值
     */

    public void sendSimpleMail(String to, String subject, String content) {
        //创建一个简单文本邮件的对象

        SimpleMailMessage message = new SimpleMailMessage();
        //赋予相应的内容
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        //将邮件对象赋予邮件发送器
        mailSender.send(message);
    }


    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
        }
    }
}