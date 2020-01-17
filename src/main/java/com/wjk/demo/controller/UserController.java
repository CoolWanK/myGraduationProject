package com.wjk.demo.controller;

import com.baidu.aip.util.Base64Util;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wjk.demo.handler.ListHandle;
import com.wjk.demo.pojo.Orders;
import com.wjk.demo.pojo.User;
import com.wjk.demo.service.OrdersService;
import com.wjk.demo.service.UserService;
import com.wjk.demo.utils.BaiDuFaceUtil;
import net.bytebuddy.asm.Advice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UserService userService;
    @RequestMapping("register")
    public String register(){
        return "user/register";
    }
    @PostMapping("register-finish")
    public String registerfinish(User user, HttpSession session, Model model){
        userService.saveUser(user);
        session.setAttribute("user",user);
        model.addAttribute("session",session);
        return "user/user";
    }
    @RequestMapping("login")
    public String login(){
        return "user/login";
    }
    @PostMapping("login-finish")
    public String loginfinish(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password, HttpSession
             session, RedirectAttributes attributes, Model model){
             User user=userService.checkLogin(username,password);
                if (user!=null){
                    user.setPassword(null);
                    session.setAttribute("user",user);
                    model.addAttribute("session",session);
                    return "redirect:/";
                }else{
                    attributes.addFlashAttribute("message","账号或密码错误");
                    return "redirect:login";
                }
    }

    @GetMapping("info")
    public String info(HttpSession session, Model model){
        if (session.getAttribute("user")!=null){
            model.addAttribute("user",session.getAttribute("user"));
            return "user/user";
        }else {
            return "redirect:login";
        }

    }
    @PostMapping("edit-finish")
    public String editfinish(User user,Model model,HttpSession session){
        userService.updateById(user);
        session.setAttribute("user",user);
        model.addAttribute("user",session.getAttribute("user"));
        return "user/user";
    }
    @RequestMapping("user-orders/{pageNum}")
    public String user_orders(@PathVariable Integer pageNum, Model model,HttpSession session){
        User user= (User) session.getAttribute("user");
        System.out.println(user.getId());
        List<Orders> ordersList=ordersService.findAllOrdersByUserId(user.getId());
        List<Orders>list= ListHandle.startPage(pageNum,5,ordersList);
        model.addAttribute("pageInfo",list);
        model.addAttribute("pageNum",ListHandle.page);
        model.addAttribute("pageFirst",ListHandle.isFirstPage);
        model.addAttribute("pageLast",ListHandle.isLastPage);
        return "user/user-order";
    }
    @RequestMapping("delete-session")
    public String delete_session(HttpSession session){
        session.setAttribute("user",null);
        return "redirect:/";
    }
    @RequestMapping("num")
    public String num(@RequestParam(value = "path")String path,Model model,HttpSession session){
        if (path.equals("1")){
            User user= (User) session.getAttribute("user");
            model.addAttribute("user",user);
        }
        if (path.equals("2")){
            User user= (User) session.getAttribute("user");
            System.out.println(user.getId());
            List<Orders> ordersList=ordersService.findAllOrdersByUserId(user.getId());
            List<Orders>list= ListHandle.startPage(1,2,ordersList);
            model.addAttribute("pageInfo",list);
        }
        return "user/"+path;
    }
    @RequestMapping("order-songDa/{id}")
    public String order_songDa(@PathVariable Integer id){
        Orders o=ordersService.findOrdersById(id);
        o.setBusiness_order(1);
        o.setUser_time(new Date());
        ordersService.updateOrderByOrder(o);
        return "redirect:/user/user-orders/1";
    }
    @Autowired
    private BaiDuFaceUtil baiDuFaceUtil;
    @ResponseBody
    @PostMapping("photo-check")
    public String photoCheck(@RequestParam(value = "url") String url){
        url=url.substring(23);

//        System.out.println(url);
        Boolean b =baiDuFaceUtil.faceCheck(url);
        if (b){
           String id=baiDuFaceUtil.faceSearch(url);
           if (id!=null){
               return id;
           }else {
               return "0";
           }

        }else {
           return "-1";
        }

    }

    @PostMapping("photo-renlian-check")
    public String photo_renlian_check(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "update",defaultValue = "0")Integer update, HttpSession session,Model model) throws IOException {
        String str="";
        User user= (User) session.getAttribute("user");
        String userId=user.getId().toString();
       byte[] bytes=file.getBytes();
        String image=Base64Util.encode(bytes);

        Boolean b = baiDuFaceUtil.faceCheck(image);
        if (b){
            if (update==0){
                baiDuFaceUtil.faceRegister(userId,image);
                user.setPhoto_register(1);
                userService.updateById(user);
                model.addAttribute("user",user);
                model.addAttribute("msg","上传成功");
                System.out.println("上传成功");
            }else {
                baiDuFaceUtil.faceUpdate(userId,image);
                model.addAttribute("user",user);
                model.addAttribute("msg","上传更新成功");
                System.out.println("上传更新成功");
            }

        }else {
            model.addAttribute("user",user);
            model.addAttribute("msg","上传失败请选择人脸图片");
            System.out.println("上传失败");
        }
        return "user/user";
    }
    @RequestMapping("photo-login")
    public String photo_login(){
        return "user/photo";
    }
    @PostMapping("renlian-login")
    public String renlian_login(@RequestParam(value = "id")Integer id,HttpSession session){
        System.out.println(id);
        User user=userService.getUser(id);
        System.out.println("123");
        session.setAttribute("user",user);
        return "redirect:/";
    }
}
