package com.group5.hawadeeleasemanagementsystem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group5.hawadeeleasemanagementsystem.dao.ClientDao;
import com.group5.hawadeeleasemanagementsystem.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientDao clientDao;

    @Autowired
    MailService mailService;
 /*
 从数据库中找到所有client并分页
  */
    public PageInfo<Client> getClientByPage(Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Client> clientList = clientDao.getClients();
        PageInfo<Client> pageInfo = new PageInfo<Client>(clientList);
        return pageInfo;
    }

    /*
    判断前端传入的数据是否为重复，解决刷新重复提交的问题
     */
    public boolean isRepeated(Client client){
        if(clientDao.selectClient(client) == null){
            return false;
        } else {
            return true;
        }
    }
    /*
    向client表中插入一条数据
     */
    public void addClient(Client client){

        clientDao.addClient(client);
    }

    /*
    根据id删除客户
     */
    public void deleteById(Integer id){clientDao.deleteById(id);}

    /*
    向所有客户群发邮件
     */
    public void sendEmail(String subject,String content){
        String email;
        List<String> emailList = clientDao.getAllEmail();
        Iterator<String> iterator = emailList.iterator();
        while (iterator.hasNext()){
            email = iterator.next();
            mailService.sendHtmlMail(email,subject,content);
        }
    }
}
