package com.group5.hawadeeleasemanagementsystem.service;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

@Service
public class SMSService {
    private com.aliyun.dysmsapi20170525.Client createClient(String accessKey, String accessKeySecret) throws Exception {
        Config config = new Config().setAccessKeyId(accessKey).setAccessKeySecret(accessKeySecret);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    private String generateCode(){
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }

    public String sendVerificationCode(String phoneNumber){
        String code = generateCode();
        System.out.println("Code: " + code + "  has sent to: " + phoneNumber);
        try {
            com.aliyun.dysmsapi20170525.Client client =
                    createClient(AccessKeyId, AccessKeySecret);
            SendSmsRequest request = new SendSmsRequest()
                    .setSignName("ABC商城")
                    .setTemplateCode("SMS_197871297")
                    .setPhoneNumbers(phoneNumber)
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            client.sendSms(request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }

    private static final String AccessKeyId = "LTAI4G3bsoy3MiAvNeMPLqZw";
    private static final String AccessKeySecret = "0hXrGe4zrHScM9mW3Q1DFaTTANRBWh";
}
