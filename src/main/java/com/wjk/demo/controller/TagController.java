package com.wjk.demo.controller;

import com.wjk.demo.pojo.Tag;
import com.wjk.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @RequestMapping("tag-input")
    public String insertTag( ){

        return "business/tag-input";
    }
    @PostMapping("tag-input-finish")
    public String insertTagfinishi(Tag tag, RedirectAttributes attributes, Model model){
        Tag t=tagService.findTag(tag.getName());
        if (t!=null){
            attributes.addFlashAttribute("message","此标签已存在");
            return "redirect:tag-input";
        }
        tagService.insert(tag);
        return "business/tag-input";
    }
}
