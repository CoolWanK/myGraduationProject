package com.wjk.demo.mapper;

import com.wjk.demo.pojo.Comments;
import com.wjk.demo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper{
 void saveComment(Comments comments);
 List<Comments>findAllComments(Integer business_id);
 User findFatherUserById(Integer id);
}
