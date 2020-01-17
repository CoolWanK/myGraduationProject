package com.wjk.demo.controller;

import com.wjk.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;

}
