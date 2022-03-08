package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PDFService {
    public static void write(String resultFilePath, User user, String reimbursementAmount, String reimbursementContent) throws Exception {
        final String filePath = FileService.FilePath;
        List<PdfPro> pdfList = new ArrayList<>();
        //text: --x:179.54--y:604.86--width:5.279999--height:11.071045
        //id
        pdfList.add(new PdfPro(270.25f, 680.42f, "" + user.getId()));
        //姓名
        pdfList.add(new PdfPro(380.54f, 680.42f, user.getName()));
        //amount
        pdfList.add(new PdfPro(309.54f, 608.62f, reimbursementAmount));
        //reason
        pdfList.add(new PdfPro(309.54f, 648.62f, reimbursementContent));
        ArrayList<ImagePro> imgList = new ArrayList();
        final String imagePath = filePath + "\\huadi.png";
        // scalePercent 缩放比例
        imgList.add(new ImagePro(340.74f, 420f, 100, imagePath));
        final String templatePath = filePath + "\\template.pdf";
        EditorPDF.writeToPdf(templatePath, resultFilePath, imgList, pdfList);
    }
}
