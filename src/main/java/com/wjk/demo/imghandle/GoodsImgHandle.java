package com.wjk.demo.imghandle;


import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

public class GoodsImgHandle {

   public static void savePic(MultipartFile file, int id, Goods goods) throws Exception {
       if (file.isEmpty()){
           System.out.println("此文件为空");
       }
       String fileName=file.getOriginalFilename();
       String uuid=UUID.randomUUID().toString();
       String suff=fileName.split("\\.")[1];
       fileName=uuid+"."+suff;
       String path="C:\\Users\\Administrator\\Desktop\\images\\"+"business"+id+"\\"+"goods/";
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
       goods.setImg("business"+id+"\\"+"goods/"+fileName);
       FileOutputStream fileOutputStream=new FileOutputStream(path+fileName);
       while ((len=inputStream.read(b))!=-1){
           fileOutputStream.write(b,0,len);
       }

        inputStream.close();
       fileOutputStream.close();
   }
}
