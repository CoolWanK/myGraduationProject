package com.wjk.demo.imghandle;






import com.wjk.demo.pojo.Business;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
public class ImgHandle {

   public static void savePic(MultipartFile file, int id, Business business) throws Exception {
       if (file.isEmpty()){
           System.out.println("此文件为空");
       }
       String fileName=file.getOriginalFilename();
       String uuid=UUID.randomUUID().toString();
       String suff=fileName.split("\\.")[1];
       fileName=uuid+"."+suff;
       String path="C:\\Users\\Administrator\\Desktop\\images\\"+"business"+id+"\\"+"type/";
       File file1=new File(path);
       if (!file1.exists()){
           file1.mkdirs();
           System.out.println("创建新的路径");
       }else {
           System.out.println("存在的路劲");
       }
       File file2=new File(path);
       InputStream inputStream=file.getInputStream();
       byte[] b=new byte[1024];
       int len=0;
       business.setImg("business"+id+"\\"+"type/"+fileName);
       FileOutputStream fileOutputStream=new FileOutputStream(path+fileName);
       while ((len=inputStream.read(b))!=-1){
           fileOutputStream.write(b,0,len);
       }

        inputStream.close();
       fileOutputStream.close();
   }
}
