package com.group5.hawadeeleasemanagementsystem;

import com.group5.hawadeeleasemanagementsystem.dao.ClientDao;
import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.domain.Client;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.service.MailService;
import com.group5.hawadeeleasemanagementsystem.service.PDFService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PDFTests {
    @Autowired
    PDFService pdfService;

    @Test
    public void testPDF() throws Exception {
        String resultFilePath = "D:\\code is here\\hawadeeFile\\new.pdf";
        User user = new User();
        user.setId(1);
        user.setName("lzlz");
        user.setPhone("153654165146");
        String reimbursementAmount = "100";
        String reimbursementContent = "chucai";
        pdfService.write(resultFilePath, user, reimbursementAmount, reimbursementContent);
    }
}
