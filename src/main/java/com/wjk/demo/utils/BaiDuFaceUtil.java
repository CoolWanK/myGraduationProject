package com.wjk.demo.utils;

import com.baidu.aip.face.AipFace;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class BaiDuFaceUtil {
    public static final String APP_ID="18028434";
    public static final String APP_KEY="zxOidhDplnHZQ14xZItwFacI";
    public static final String SECRET_KEY="4A4eRimHhmc3ZCT66I4pqKHfjxfBGQ8D";
    public static final String IMAGE_TYPE="BASE64";
    String groupId="wjk";
     AipFace client;
    HashMap<String,String>options=new HashMap<>();
    public BaiDuFaceUtil(){
        options.put("quality_control","NORMAL");
        options.put("face_type", "LIVE");

    }
    @PostConstruct
    public void init(){
        client=new AipFace(APP_ID,APP_KEY,SECRET_KEY);
    }

    /**
     *
     * @param userId
     * @param image
     * @return
     * 人脸注册将人脸图片存入库中
     */

    public Boolean faceRegister(String userId,String image){
        JSONObject jsonObject=client.addUser(image,IMAGE_TYPE,groupId,userId, options);
        Integer errCode=jsonObject.getInt("error_code");
        return errCode == 0 ? true:false;
    }
    /**
     * 人脸更新
     */
    public Boolean  faceUpdate(String userId,String image){
        JSONObject jsonObject=client.updateUser(image,IMAGE_TYPE,groupId,userId, options);
        Integer errCode=jsonObject.getInt("error_code");
        return errCode == 0 ? true:false;
    }

    /**
     *人脸检测
     */
    public Boolean faceCheck(String image){
        JSONObject jsonObject=client.detect(image,IMAGE_TYPE,options);
        if (jsonObject.has("error_code")&&jsonObject.getInt("error_code")==0){
            JSONObject resultJson=jsonObject.getJSONObject("result");
            Integer faceNum=resultJson.getInt("face_num");
            return faceNum== 1 ? true:false;
        }else {
            return false;
        }
    }
    /**
     * 人脸查找
     */
    public String faceSearch(String image){
        JSONObject jsonObject=client.search(image,IMAGE_TYPE,groupId,options);
        if (jsonObject.has("error_code")&&jsonObject.getInt("error_code")==0){
            JSONObject resultJson=jsonObject.getJSONObject("result");
            JSONArray userList=resultJson.getJSONArray("user_list");
            if (userList.length()>0){
                JSONObject js= userList.getJSONObject(0);
                Double score=js.getDouble("score");
                if (score>80){
                    return js.getString("user_id");
                }
            }
        }
        return null;
    }

}
