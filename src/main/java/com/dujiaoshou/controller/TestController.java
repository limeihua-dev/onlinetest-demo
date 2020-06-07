package com.dujiaoshou.controller;


import com.dujiaoshou.model.Question;
import com.dujiaoshou.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/test")
    public String index(Model model){
        List<Question> questionList = questionService.list();
        model.addAttribute(questionList);
        return "test";
    }


}
