package com.wjk.demo;

import com.wjk.demo.mapper.CommentMapper;
import com.wjk.demo.pojo.Comments;
import com.wjk.demo.service.CommentService;
import com.wjk.demo.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Test {
    @org.junit.Test
    public void t1(){
        CommentService commentService=new CommentServiceImpl();
        List<Comments> commentsList=commentService.findAllComments(1);
        for (Comments c:commentsList){
            System.out.println(c.getId()+"  :"+ c.getContent());
            for (Comments c1:c.getReplyCommentList()){
                System.out.println(c1.getId()+"  :"+ c1.getContent());
            }
        }
    }
}
