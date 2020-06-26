package com.dujiaoshou.controller;


import com.dujiaoshou.service.QuestionService;
import com.dujiaoshou.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/test")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "limit",defaultValue = "5") Integer limit){
        PaginationVO paginationVO = questionService.list(page, limit);
        model.addAttribute(paginationVO);
        return "test1";
    }



}
