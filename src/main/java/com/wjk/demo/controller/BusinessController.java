package com.wjk.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wjk.demo.handler.ListHandle;
import com.wjk.demo.imghandle.GoodsImgHandle;
import com.wjk.demo.imghandle.ImgHandle;
import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Goods;
import com.wjk.demo.pojo.Orders;
import com.wjk.demo.pojo.Type;
import com.wjk.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("business")
public class BusinessController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BusinessService businessService;
    @RequestMapping("register")
    public String register(){
        return "business/business-register";
    }
    @PostMapping("register-finish")
    public String registerfinish(Business business, Model model, HttpSession session){
        business.setFlag(0);
        business.setCount(0);
        businessService.insert(business);
      Business business1 = businessService.findByNickname(business.getNickname());
        session.setAttribute("business",business1);
        return "redirect:/";
    }
    @RequestMapping("info")
    public String info(Model model,HttpSession session){
        if (session.getAttribute("business")==null){
            return "business/login";
        }
        return "business/business";
    }
    @RequestMapping("num")
    public String one(@RequestParam(value = "path") String path,@RequestParam(value ="pageNum",defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "8")Integer pageSize, Model model,HttpSession session){
        int i=0;
        model.addAttribute("types",typeService.findAll());
        Business business= (Business) session.getAttribute("business");
        session.setAttribute("business",businessService.getType(business.getId()));
        if (path.equals("2")){
            Business business1= (Business) goodsService.findAllGoods(business.getId());
            try{
                business1.getGoodsList();
            }catch (Exception e){
                e.printStackTrace();
                i=1;
                System.out.println("空异常");
            }
            if (i==0){
                PageHelper.startPage(pageNum,pageSize);
                List<Goods>goodsList=business1.getGoodsList();
                PageInfo pageInfo=new PageInfo<Goods>(goodsList);
                model.addAttribute("goodsList",pageInfo);
                //获得当前页数
                model.addAttribute("pageNum",pageInfo.getPageNum());
                //获得总页数
                model.addAttribute("pageCount",pageInfo.getPages());
                //是否第一页数
                model.addAttribute("pageFirst",pageInfo.isIsFirstPage());
                //是否最后一页
                model.addAttribute("pageLast",pageInfo.isIsLastPage());
                //获得当前页数量
                model.addAttribute("pageNumSize",pageInfo.getPageSize());
            }
        }

           if(path.equals("3")){

               List<Orders>ordersList=ordersService.findAllOrders(business.getId());
               System.out.println(ordersList.size());
               List<Orders> list=ListHandle.startPage(1,3,ordersList);
               model.addAttribute("pageInfo",list);
           }
        return "business/"+path;
    }
    @GetMapping("login")
    public String login(){
        return "business/login";
    }
    @PostMapping("login-finish")
    public String login_finish(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password, HttpSession session, Model model, RedirectAttributes attributes){
        Business business=businessService.findByPassword(username,password);
        if (business!=null){
            business.setPassword(null);
            session.setAttribute("business",business);
            return "redirect:/";
        }else {
            attributes.addFlashAttribute("message","用户账号或密码错误");
            return "redirect:login";
        }


    }

    @PostMapping("edit")
    public String edit(@RequestParam(value ="type.id") Integer typeid , @RequestParam(value = "file")MultipartFile file, Business business, Model model, HttpSession session, HttpServletRequest request) throws Exception {
        if (!file.isEmpty()){
            ImgHandle.savePic(file,business.getId(),business);
        }
        businessService.insertType(typeid,business.getId());
        businessService.update(business);
         Business business1= businessService.getType(business.getId());
        session.setAttribute("business",business1);
        System.out.println(business1.getType().getName());


        return "business/business";
    }
    @GetMapping("goods-input")
    public String good_input(Model model){
        model.addAttribute("tags",tagService.findAll());
        return "business/menu-input";
    }
    @PostMapping("goods-input-finish")
    public String goods_inpput_finishi(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "tag.id")Integer tagid, Goods goods,Model model,HttpSession session) throws Exception {
        Business business= (Business) session.getAttribute("business");
       goods.setTag_id(tagid);
       goods.setBusiness_id(business.getId());
       goods.setCount(0);
        GoodsImgHandle.savePic(file,business.getId(),goods);
        goodsService.insertGoods(goods);
        model.addAttribute("message","2");
        return "business/business";
    }
    @RequestMapping("listPage/{pageNum}")
    public String listPage(@PathVariable Integer pageNum,Model model){
        model.addAttribute("message","2");
        model.addAttribute("pageNum",pageNum);
        return "business/business";
    }
    @RequestMapping("goods-edit/{id}")
    public String goods_edit(@PathVariable Integer id,Model model){
       model.addAttribute("goods",goodsService.findGoodsById(id));
       model.addAttribute("tags",  tagService.findAll());
        return "business/menu-edit";
    }

    @PostMapping("goods-edit-finish")
    public String goods_edit_finish(@RequestParam(value = "tag.id")Integer tagid,@RequestParam(value = "file")MultipartFile file, Goods goods,Model model,HttpSession session,RedirectAttributes attributes) throws Exception {
                if (tagid!=null){
                    goods.setTag_id(tagid);
                }
                goods.setCreateTime(new Date());

                Business business= (Business) session.getAttribute("business");
                if (!file.isEmpty()){
                    GoodsImgHandle.savePic(file,business.getId(),goods);
                }

                goodsService.updateGoods(goods);

                model.addAttribute("message","2");
                return "business/business";
    }
    @RequestMapping("goods-delete/{id}")
    public String goods_delete(@PathVariable Integer id, Model model){
        goodsService.deleteGoodsById(id);
        model.addAttribute("message","2");
        return "business/business";
    }
    @RequestMapping("reLogin")
    public String reLogin(HttpSession session){
        session.setAttribute("business",null);
        return "business/login";
    }
    @ResponseBody
    @RequestMapping("yanzheng1")
    public String yanzheng(@RequestParam(value = "username")String username, HttpServletResponse response) throws IOException {
           String s="";
            List<String>usernames=businessService.findAllUsername();
            if (usernames.contains(username)){
                s="已存在该用户名";
            }else {
               s="可以使用";
            }
            return s;
    }
    @ResponseBody
    @RequestMapping("yanzheng2")
    public String yanzheng2(@RequestParam(value = "nickname")String nickname) throws IOException {
        String s="";
        List<String>nicknames=businessService.findAllNickname();
        if (nicknames.contains(nickname)){
            s="已存在该店铺名";
        }else {
            s="可以使用";
        }
        return s;
    }
    @ResponseBody
    @RequestMapping("sale")
    public String sall(HttpSession session){
        int i=0;
        String s="上架成功";
         Business business= (Business) session.getAttribute("business");
         Business b =businessService.getType(business.getId());
         if (b.getType().getName()==null){
             s="商户类型不能为空";
             return s;
         }
         if ("".equals(b.getImg())||b.getNickname()==null||b.getStartPrice()==null||b.getPhone()==null||b.getAddress()==null){
             s="请填写完整的商家信息";
             return s;
         }
         Business business1=goodsService.findAllGoods(business.getId());
         try {
             List<Goods>goodsList=business1.getGoodsList();
         }catch (Exception e){
             e.printStackTrace();
             i=1;
         }
         if (i==1){
             s="商家上架之前最少保证要有一个商品";
         }
         business.setFlag(1);
         businessService.updateFlag(business);

         return s;
    }
    @ResponseBody
    @RequestMapping("testGoodsName")
    public String yanzhengName(@RequestParam(value = "name")String name,HttpSession session){
        String s="";
        Business business= (Business) session.getAttribute("business");
        List<String>list=goodsService.findAllGoodsNameByBusinessId(business.getId());
        if (list.contains(name)){
            s="已存在该菜品名";
            return s;
        }
        return s;
    }
    @RequestMapping("moreinfo/{pageNum}")
    public String moreInfo(HttpSession session,@PathVariable Integer pageNum,Model model){

        Business business= (Business) session.getAttribute("business");
       List<Orders>ordersList=ordersService.findAllOrders(business.getId());
       List<Orders>ordersList1= ListHandle.startPage(pageNum,4,ordersList);
       model.addAttribute("pageInfo",ordersList1);
       model.addAttribute("pageNum",ListHandle.page);
       model.addAttribute("pageFirst",ListHandle.isFirstPage);
       model.addAttribute("pageLast",ListHandle.isLastPage);


//        model.addAttribute("pageInfo",pageInfo);
//        //获得当前页数
//        model.addAttribute("pageNum",pageInfo.getPageNum());
//        //获得总页数
//        model.addAttribute("pageCount",pageInfo.getPages());
//        //是否第一页数
//        model.addAttribute("pageFirst",pageInfo.isIsFirstPage());
//        //是否最后一页
//        model.addAttribute("pageLast",pageInfo.isIsLastPage());
//        //获得当前页数量
//        model.addAttribute("pageNumSize",pageInfo.getPageSize());
        return "business/order-manger";
    }
    @ResponseBody
    @PostMapping("isFlag")
    public  String isFlag(HttpSession session){
        String str="";
        Business business= (Business) session.getAttribute("business");
        Business b=businessService.findBusinessById(business.getId());
        if (b.getFlag()==1){
            str="1";
        }else {
            str="0";
        }
        return str;
    }
    @ResponseBody
    @PostMapping("not-sale")
    public String not_sale(HttpSession session){
        Business business= (Business) session.getAttribute("business");
       business.setFlag(0);
       businessService.update(business);
       return "下架成功";
    }
    @RequestMapping("order-edit/{id}")
    public String order_edit(@PathVariable Integer id){
        ordersService.updateOrders(id);
        return "redirect:/business/moreinfo/1";
    }
    @RequestMapping("moreinfo/y/{pageNum}")
    public String moreinfo_y(@PathVariable Integer pageNum,HttpSession session,Model model){
        Business b= (Business) session.getAttribute("business");
        List<Orders>ordersList=ordersService.findAllYesOrders(b.getId());
        List<Orders> ordersList1= ListHandle.startPage(pageNum,4,ordersList);
        model.addAttribute("pageInfo",ordersList1);
        model.addAttribute("pageNum",ListHandle.page);
        model.addAttribute("pageFirst",ListHandle.isFirstPage);
        model.addAttribute("pageLast",ListHandle.isLastPage);
        return "business/order-manger-y";
    }
    @RequestMapping("moreinfo/n/{pageNum}")
    public String moreinfo_n(@PathVariable Integer pageNum, HttpSession session,Model model){
        Business b= (Business) session.getAttribute("business");
        List<Orders>ordersList=ordersService.findAllNoOrders(b.getId());
        List<Orders> ordersList1= ListHandle.startPage(pageNum,4,ordersList);
        model.addAttribute("pageInfo",ordersList1);
        model.addAttribute("pageNum",ListHandle.page);
        model.addAttribute("pageFirst",ListHandle.isFirstPage);
        model.addAttribute("pageLast",ListHandle.isLastPage);
        return "business/order-manger-n";
    }
}
