package com.group5.hawadeeleasemanagementsystem.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group5.hawadeeleasemanagementsystem.dao.ClientDao;
import com.group5.hawadeeleasemanagementsystem.domain.Client;
import com.group5.hawadeeleasemanagementsystem.service.ClientService;
import com.group5.hawadeeleasemanagementsystem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @RequestMapping("/clientPage")
    private ModelAndView  clientPage(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        ModelAndView mv = new ModelAndView();
        PageInfo<Client> pageInfo = clientService.getClientByPage(pageNum);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("/client/clientPage");
        return mv;
    }


    @RequestMapping("/add")
    private ModelAndView addClient(HttpServletRequest request){
        Client client = new Client();
        client.setName(request.getParameter("name")).setPhone(request.getParameter("phone")).setEmail(request.getParameter("email"));
        clientService.addClient(client);
        PageInfo<Client> pageInfo = clientService.getClientByPage(1);
        ModelAndView mv = new ModelAndView("/client/clientPage");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequestMapping("emailPage")
    private String emailPage(){
        return "/client/emailPage";
    }

    @RequestMapping("sendemail")
    private String sendEmail(HttpServletRequest request){
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        clientService.sendEmail(subject,content);
        return "/client/emailPage";
    }



}
