package com.group5.hawadeeleasemanagementsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    public static final String FilePath = "E:/hawadeeFile/";

    private String getUniqueFileName(String prefix, String suffix) throws Exception {
        String fileName = prefix + UUID.randomUUID() + suffix;
        File directory = new File(FileService.FilePath);
        if(!directory.exists()) {
            boolean isCreate = directory.mkdir();
            if (!isCreate) {
                throw new Exception("File create failed");
            }
        }
        return fileName;
    }

    /**
     *
     * @param file file to save
     * @return save location
     */
    public String save(MultipartFile file) throws Exception{
        String fileName = this.getUniqueFileName("", file.getOriginalFilename());
        File localFile = new File(FileService.FilePath + fileName);
        file.transferTo(localFile);
        return fileName;
    }

    public String save(String fileContent, String prefix, String suffix) throws Exception {
        String fileName = this.getUniqueFileName(prefix,suffix);
        System.out.println(fileName);
        FileWriter writer = new FileWriter(FileService.FilePath + fileName);
        writer.write("");
        writer.write(fileContent);
        writer.flush();
        writer.close();
        return fileName;
    }

    public void saveTo(String fileContent, String fileName) throws IOException {
        FileWriter writer = new FileWriter(FileService.FilePath + fileName);
        writer.write("");
        writer.write(fileContent);
        writer.flush();
        writer.close();
    }

    public String read(String fileName) throws Exception {
        return Files.readString(Paths.get(FileService.FilePath + fileName));
    }

    /**
     * 调用会有getOutputStream() has already been called for this response异常，但是不影响功能，先挂着
     */
    public void loadToServlet(String fileName, HttpServletResponse response)
            throws IOException {
        File file = new File(FileService.FilePath + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        response.reset();
        response.setHeader("content-disposition", "attachment;filename=" +
                URLEncoder.encode(FileService.FilePath + fileName, StandardCharsets.UTF_8));
        ServletOutputStream outputStream = response.getOutputStream();
        FileCopyUtils.copy(inputStream,outputStream);
    }
}
