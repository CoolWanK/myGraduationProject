package com.wjk.demo.service;

import com.wjk.demo.mapper.TagMapper;
import com.wjk.demo.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements  TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public void insert(Tag tag) {
        tagMapper.insertTag(tag);
    }

    @Override
    public Tag findTag(String name) {
        return tagMapper.findTagByName(name);
    }

    @Override
    public List<Tag> findAll() {
        return tagMapper.findAll();
    }

    @Override
    public List<Tag> findEightTags() {
        return tagMapper.findEightTags();
    }
}
