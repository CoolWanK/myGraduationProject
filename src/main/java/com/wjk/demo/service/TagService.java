package com.wjk.demo.service;

import com.wjk.demo.pojo.Tag;

import java.util.List;

public interface  TagService {
    void insert(Tag tag);
    Tag findTag(String name);
    List<Tag> findAll();
    List<Tag>findEightTags();
}
