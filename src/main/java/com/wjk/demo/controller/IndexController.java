package com.wjk.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjk.demo.pojo.*;
import com.wjk.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import javax.jnlp.IntegrationService;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller

public class IndexController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @RequestMapping("/")
    public String index(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model){
        model.addAttribute("types",typeService.findAll());
        model.addAttribute("goodsList",goodsService.findFiveGoods());
        PageHelper.startPage(pageNum,6);
        List<Business>l=businessService.findFlagBusiness();
        System.out.println(l.toString());
        PageInfo pageInfo=new PageInfo<Business>(l);
        model.addAttribute("businessList",pageInfo);
        model.addAttribute("pageNum",pageInfo.getPageNum());
        model.addAttribute("pageFirst",pageInfo.isIsFirstPage());
        model.addAttribute("pageLast",pageInfo.isIsLastPage());
        model.addAttribute("tags",tagService.findEightTags());
        return "index";
    }
    @RequestMapping("/page/{pageNum}")
    public String pageList(@PathVariable Integer pageNum){
        return "redirect:/?pageNum="+pageNum;
    }
    @RequestMapping("/typeList")
    public String typeList(Model model){
        model.addAttribute("types",typeService.findAll());
        return "_fragments::types";
    }
    @GetMapping("/search")
    public String search( String search,Model model){
        List<Goods>list=goodsService.findBySearchName(search);
        model.addAttribute("goodsList",list);
        model.addAttribute("search",search);
        model.addAttribute("tags",tagService.findEightTags());
        model.addAttribute("goodsLists",goodsService.findFiveGoods());

        return "search-success";
    }
    @RequestMapping("/business/{id}")
    public String business(@PathVariable Integer id,Model model){
        model.addAttribute("tags",businessService.findTagByBusinessId(id));
        model.addAttribute("business",businessService.findBusinessById(id));
//        List<Tag>list=businessService.findTagByBusinessId(id);
//        for (Tag t:list){
//            System.out.printf(t.getGoodsList().toString());
//        }
        return "order-info";
    }

    @PostMapping("/pay")
    public String pay(Orders orders, Integer businessId, HttpSession session){
      User user= (User) session.getAttribute("user");
      if(user==null){
          return "user/login";
      }
      Business business=businessService.findBusinessById(businessId);
      business.setCount(business.getCount()+1);
      businessService.update(business);
      List<Goods>list =orders.getGoodsList();
      List<Goods>l=new ArrayList<>();
      for (Goods g:list){
          if (g.getId()!=null){
              Integer count=g.getCount();
              count++;
              g.setCount(count);
              goodsService.updateGoods(g);
              l.add(g);
          }
      }
      Date createTime=new Date();

      orders.setGoodsList(l);
      orders.setBusiness(business);
      orders.setBusiness_id(businessId);
      orders.setUser_id(user.getId());
      orders.setUser(user);
      orders.setCreateTime(createTime);
      orders.setBusiness_order(0);
      ordersService.insertOrders(orders);
        return "pay";

    }
    @RequestMapping("/types/{id}")
    public String businessByType(@PathVariable Integer id ,Model model){
        Type type=businessService.findBusinessByTypeId(id);
        System.out.println(type.getName());
        model.addAttribute("type",type);
        model.addAttribute("goodsList",goodsService.findFiveGoods());
        model.addAttribute("tags",tagService.findEightTags());
        return "search-by-type";
    }
}
