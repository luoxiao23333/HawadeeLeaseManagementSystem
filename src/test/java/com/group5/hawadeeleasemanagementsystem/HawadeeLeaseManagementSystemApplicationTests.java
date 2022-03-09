package com.group5.hawadeeleasemanagementsystem;

import com.group5.hawadeeleasemanagementsystem.dao.ClientDao;
import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.dao.userRelDao;
import com.group5.hawadeeleasemanagementsystem.domain.Client;
import com.group5.hawadeeleasemanagementsystem.domain.userRel;
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
    @Autowired
    MailService mailService;

    userRelDao userrelDao;

    @Autowired
    void setUserRelDao(userRelDao userrelDao) {this.userrelDao = userrelDao;}

    @Test
    void contextLoads() {
    }

    @Test
    void test4() {
        userRel userrel = userrelDao.getRel(2);
        System.out.println(userrel == null);
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


    @Test
    public void sendSimpleMailTest() {
        //调用定义的发送文本邮件的方法
        mailService.sendSimpleMail("3279826458@qq.com", "这是第一封邮件", "这是邮件内容");
    }

    @Test
    public void test3(){
        Client client = new Client();
        client.setName("杜小龙").setPhone("15282505597").setEmail("1635564377@qq.com");
        Client test = clientDao.selectClient(client);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(test.getId());
    }
}
