package com.group5.hawadeeleasemanagementsystem;

import com.group5.hawadeeleasemanagementsystem.dao.ClientDao;
import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.domain.Client;
import com.group5.hawadeeleasemanagementsystem.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
class HawadeeLeaseManagementSystemApplicationTests {

    private ClientDao clientDao;
    @Autowired
    private void setClientDao(ClientDao clientDao){
        this.clientDao = clientDao;
    }

    @Test
    void contextLoads() {
    }


    @Test
    void test1(){
        Client client = new Client();
        client.setName("胡歌").setPhone("19822902915").setEmail("146891@qq.com");
        clientDao.addClient(client);
    }

    @Test
    void test2(){
        List<Client> clients = clientDao.getClients();
        Client client = new Client();
        Iterator<Client> clientIterator = clients.iterator();
        while(clientIterator.hasNext()){
            client = clientIterator.next();
            System.out.println(client.getId()+" "+client.getName()+" "+client.getPhone()+" "+client.getEmail());
        }
    }

    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMailTest() {
        //调用定义的发送文本邮件的方法
        mailService.sendSimpleMail("3279826458@qq.com", "这是第一封邮件", "这是邮件内容");
    }

}
