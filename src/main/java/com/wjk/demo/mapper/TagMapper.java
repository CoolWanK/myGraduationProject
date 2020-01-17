package com.wjk.demo.mapper;

import com.wjk.demo.pojo.Tag;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;

@Repository
public interface TagMapper {
    void insertTag(Tag tag);
    Tag findTagByName(String name);
    List<Tag> findAll();
    List<Tag>findEightTags();
}