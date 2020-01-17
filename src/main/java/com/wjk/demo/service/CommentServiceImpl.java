package com.wjk.demo.service;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.wjk.demo.mapper.CommentMapper;
import com.wjk.demo.mapper.UserMapper;
import com.wjk.demo.pojo.Comments;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void saveComment(Comments comments) {
        commentMapper.saveComment(comments);
    }
@Transactional
    @Override
    public List<Comments> findAllComments(Integer business_id) {
       List<Comments>commentsList= eachComment(commentMapper.findAllComments(business_id));
       for (Comments c:commentsList){
           c.setUser( userMapper.getUserById(c.getUser_id()));
           for (Comments c1:c.getReplyCommentList()){
               c1.setUser(userMapper.getUserById(c1.getUser_id()));
               c1.setParentUser(commentMapper.findFatherUserById(c1.getId()));
           }

       }
        return commentsList;
    }
    private List<Comments>eachComment(List<Comments>comments){
        List<Comments> comments1=new ArrayList<>();
        if (comments.size()>0){
            for (Comments c:comments){
                List<Comments>commentsList= replyList(c);
                sortCommentByTime(commentsList);
               c.setReplyCommentList(commentsList);
               comments1.add(c);
               replCommentsList=new ArrayList<>();
            }
        }
        return comments1;
    }
    private List<Comments>replCommentsList=new ArrayList<>();
    private List<Comments> replyList(Comments comments){
        if (comments.getReplyCommentList().size()>0){
            for (Comments c:comments.getReplyCommentList()){
                replCommentsList.add(c);
                if (c.getReplyCommentList().size()>0){
                    replyList(c);
                }
            }
        }
        return replCommentsList;
    }
    private List<Comments> sortCommentByTime(List<Comments> comments) {
        Collections.sort(comments, new Comparator<Comments>() {
            @Override
            public int compare(Comments o1, Comments o2) {
                int i = o1.getCommentTime().compareTo(o2.getCommentTime());
                if (i >= 0) {
                    return 1;
                } else {
                    return -1;
                }

            }
        });
        return comments;
    }
}
