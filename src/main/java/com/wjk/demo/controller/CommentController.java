package com.wjk.demo.controller;

import com.wjk.demo.pojo.Business;
import com.wjk.demo.pojo.Comments;
import com.wjk.demo.pojo.User;
import com.wjk.demo.service.CommentService;
import com.wjk.demo.utils.CommentsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/comments-user")
    public String comment_user(Comments comments, HttpSession session){
            User user= (User) session.getAttribute("user");
            if (user==null){
                return "user/login";
            }
            comments.setIsBusiness(0);
            comments.setUser_id(user.getId());
            comments.setCommentTime(new Date());
            commentService.saveComment(comments);
        return "redirect:/business/"+comments.getBusiness_id();
    }

    @RequestMapping("/c")
    public String c(@RequestParam(value = "business_id") String business_id, Model model ){
        Integer businessid=Integer.valueOf(business_id);
        List<Comments>list=commentService.findAllComments( businessid);

        for (Comments c:list){
            System.out.println("首评论"+c.getId());
            System.out.println(c.getUser());
            for (Comments c1:c.getReplyCommentList()){
                System.out.println(c1.getId());
                System.out.println(c1.getUser().getNickname());
                System.out.println(c1.getParentUser().getNickname());
            }
        }
        model.addAttribute("commentList",list);
        return "order-info::commentList";
    }

    @RequestMapping("/comments-business")
    public String comment_business(Comments comments, HttpSession session){
        Business business = (Business) session.getAttribute("business");
        comments.setUser_id(2);
        comments.setIsBusiness(1);
        comments.setCommentTime(new Date());
        commentService.saveComment(comments);
        return "redirect:/c"+"?business_id="+comments.getBusiness_id();
    }

}
