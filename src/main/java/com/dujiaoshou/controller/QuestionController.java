package com.dujiaoshou.controller;

import com.dujiaoshou.model.Question;
import com.dujiaoshou.service.QuestionService;
import com.dujiaoshou.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //试题页面列表
    @RequestMapping("/list")
    @ResponseBody
    public DataVO questionList(Integer page, Integer limit) {

        DataVO<Question> dataVO = questionService.findData(page, limit);
        return dataVO ;
    }

    @GetMapping("/questionList")
    public String redirect(){
        return "questionList";
    }





}
