package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.dao.ClientDao;
import com.group5.hawadeeleasemanagementsystem.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/client")
public class ClientController {

    private ClientDao clientDao;
    @Autowired
    private void setClientDao(ClientDao clientDao){
        this.clientDao = clientDao;
    }

    @RequestMapping("/clientPage")
    private String clientPage(){
        return "/client/clientPage";
    }

    @RequestMapping("emailPage")
    private String emailPage(){
        return "/client/emailPage";
    }

    @RequestMapping("/list")
    private ModelAndView listClients(HttpServletRequest request){
        return new ModelAndView("/index");
    }

    @RequestMapping("/add")
    private String addClient(HttpServletRequest request){
        Client client = new Client();
        client.setName(request.getParameter("name")).setPhone(request.getParameter("phone")).setEmail(request.getParameter("email"));
        clientDao.addClient(client);
        return "/client/clientPage";
    }
}
