package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FileService {
    public static final String FilePath = "D:\\code is here\\hawadeeFile\\";

    /**
     *
     * @param file file to save
     * @return save location
     */
    public String save(MultipartFile file) throws Exception {
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        File directory = new File(FileService.FilePath);
        if (!directory.exists()) {
            boolean isCreate = directory.mkdir();
            if (!isCreate) {
                throw new Exception("File create failed");
            }
        }

        File localFile = new File(FileService.FilePath + fileName);
        file.transferTo(localFile);
        return fileName;
    }

    /**
     * 调用会有getOutputStream() has already been called for this response异常，但是不影响功能，先挂着
     */
    public void loadToServlet(String fileName, HttpServletResponse response)
            throws IOException {
        File file = new File(FileService.FilePath + fileName);
        System.out.println("FileService.FilePath + fileName = " + FileService.FilePath + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        response.reset();
        response.setHeader("content-disposition", "attachment;filename=" +
                URLEncoder.encode(FileService.FilePath + fileName, StandardCharsets.UTF_8));
        ServletOutputStream outputStream = response.getOutputStream();
        FileCopyUtils.copy(inputStream, outputStream);
    }

    public String approve(User user, String reimbursementTitle, String reimbursementContent) throws Exception {
        String resultFileName = reimbursementTitle + UUID.randomUUID() + ".pdf";
        String resultFilePath = FileService.FilePath + resultFileName;
        String reimbursementAmount = reimbursementTitle;
        PDFService.write(resultFilePath, user, reimbursementAmount, reimbursementContent);
        return resultFileName;
    }
}
