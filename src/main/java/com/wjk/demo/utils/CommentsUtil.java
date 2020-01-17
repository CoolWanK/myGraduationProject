package com.wjk.demo.utils;

import com.wjk.demo.pojo.Comments;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

public class CommentsUtil {
   public static List<Comments> indexComments=new ArrayList<>();
    public  List<Comments> sortComments(List<Comments> comments){
        if (comments!=null&&comments.size()>0){
            for (Comments c:comments){
                List<Comments> commentsList=new ArrayList<>();
                if (c.getReplyCommentList()!=null&&c.getReplyCommentList().size()>0){
                    commentsList.addAll(c.getReplyCommentList());
                    sortComments(c.getReplyCommentList());
                }else {
                    c.setReplyCommentList(commentsList);
                    indexComments.add(c);
                }
            }
        }

        return indexComments;
    }
}
