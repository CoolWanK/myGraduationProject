package com.wjk.demo.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QrCodeUtil {
    /**
     * 生成二维码
     */
    public String createQrCode(String content) throws  IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        QRCodeWriter writer=new QRCodeWriter();
        BitMatrix matrix= null;
        try {
            matrix = writer.encode(content, BarcodeFormat.QR_CODE,200,200);
            BufferedImage bufferedImage= MatrixToImageWriter.toBufferedImage(matrix);
            ImageIO.write(bufferedImage,"png",os);
            return new String("data:image/png;base64,"+ Base64.encode(os.toByteArray()));
        } catch (WriterException e) {
            e.printStackTrace();
        }finally {
            os.close();
        }
      return null;
    }
}
