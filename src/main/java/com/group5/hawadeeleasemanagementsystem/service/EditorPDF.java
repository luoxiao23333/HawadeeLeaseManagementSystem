package com.group5.hawadeeleasemanagementsystem.service;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class EditorPDF {

    /**
     * @param inputPDFFilePath  要写入的pdf文件路径
     * @param outPutPDFFilePath 输出的pdf文件路径
     * @param imagePros         要写入的图片的list,包含图片坐标等
     * @param pdfList           要写入的文字的list,包含坐标等
     * @throws Exception
     */
    public static void writeToPdf(String inputPDFFilePath, String outPutPDFFilePath, List<ImagePro> imagePros, List<PdfPro> pdfList)
            throws Exception {
        //append 追加
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(outPutPDFFilePath), false));
        PdfReader reader = new PdfReader(inputPDFFilePath);
        PdfStamper stamper = new PdfStamper(reader, bos);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;
        // BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        // "c:\\windows\\fonts\\SIMHEI.TTF" 使用windows系统的黑体
        BaseFont base = BaseFont.createFont("c:\\windows\\fonts\\SIMHEI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        PdfGState gs = new PdfGState();
        for (int i = 1; i < total; i++) {
            //content = stamper.getOverContent(i);// 在内容上方加水印
            content = stamper.getUnderContent(i);//在内容下方加水印
            gs.setFillOpacity(0.2f);
            content.beginText();
            //字体大小
            content.setFontAndSize(base, 15.5F);
            //content.setTextMatrix(390, 810);
            //内容居中，横纵坐标，偏移量
            for (PdfPro pdfPro : pdfList) {
                content.showTextAligned(Element.ALIGN_CENTER, pdfPro.getText(), pdfPro.getX(), pdfPro.getY(), 0);
            }
            for (ImagePro imagePro : imagePros) {
                //添加图片
                Image image = Image.getInstance(imagePro.getImgPath());
            /*
              img.setAlignment(Image.LEFT | Image.TEXTWRAP);
              img.setBorder(Image.BOX); img.setBorderWidth(10);
              img.setBorderColor(BaseColor.WHITE); img.scaleToFit(100072);//大小
              img.setRotationDegrees(-30);//旋转
             */
                //图片的位置（坐标）
                image.setAbsolutePosition(imagePro.getX(), imagePro.getY());
                // image of the absolute
                image.scaleToFit(500, 500);
                image.scalePercent(imagePro.getScalePercent());//依照比例缩放. 调整缩放,控制图片大小
                content.addImage(image);
            }
            content.setFontAndSize(base, 8);
            content.endText();
        }
        stamper.close();
        //关闭打开的原来PDF文件，不执行reader.close()删除不了（必须先执行stamper.close()，否则会报错）
        reader.close();
        //删除原来的PDF文件
        /*File targetTemplePDF = new File(inputPDFFilePath);
        targetTemplePDF.delete();*/
    }
}
