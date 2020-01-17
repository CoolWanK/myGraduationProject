package com.wjk.demo.service;

import com.wjk.demo.pojo.Comments;
import com.wjk.demo.pojo.User;

import java.util.List;

public interface CommentService {
    void saveComment(Comments comments);
    List<Comments> findAllComments(Integer business_id);

}
