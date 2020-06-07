package com.dujiaoshou.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dujiaoshou.mapper.QuestionMapper;
import com.dujiaoshou.model.Question;
import com.dujiaoshou.model.User;
import com.dujiaoshou.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QuestionEdit {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable(name = "id") Long id,
//                       Model model) {
//        //根据问题id从数据库中找到相关问题和user
//        Question question = questionMapper.selectById(id);
//        model.addAttribute("title", question.getTitle());
//        model.addAttribute("optionA", question.getOptionA());
//        model.addAttribute("optionB", question.getOptionB());
//        model.addAttribute("optionC", question.getOptionC());
//        model.addAttribute("optionD", question.getOptionD());
//        model.addAttribute("answer",question.getAnswer());
//        model.addAttribute("score", question.getScore());
//        return "test";
//    }


    @GetMapping("/qEdit")
    public String redirect(){
        return "questionEdit";
    }


    @PostMapping("/add")
    public String doPublish(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "optionA", required = false) String optionA,
            @RequestParam(value = "optionB", required = false) String optionB,
            @RequestParam(value = "optionC", required = false) String optionC,
            @RequestParam(value = "optionD", required = false) String optionD,
            @RequestParam(value = "answer", required = false) String answer,
            @RequestParam(value = "score", required = false) int score,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("optionA", optionA);
        model.addAttribute("optionB", optionB);
        model.addAttribute("optionC", optionC);
        model.addAttribute("optionD", optionD);
        model.addAttribute("answer", answer);
        model.addAttribute("score", score);

        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            return "questionEdit";
        }
        if (StringUtils.isBlank(optionA)) {
            model.addAttribute("error", "选项不能为空");
            return "questionEdit";
        }
        if (StringUtils.isBlank(optionB)) {
            model.addAttribute("error", "选项不能为空");
            return "questionEdit";
        }
        if (StringUtils.isBlank(optionC)) {
            model.addAttribute("error", "选项不能为空");
            return "questionEdit";
        }
        if (StringUtils.isBlank(optionD)) {
            model.addAttribute("error", "选项不能为空");
            return "questionEdit";
        }
        if (StringUtils.isBlank(answer)) {
            model.addAttribute("error", "正确答案不能为空");
            return "questionEdit";
        }
        if (score==0) {
            model.addAttribute("error", "分值不能为空");
            return "questionEdit";
        }


        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "questionEdit";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setOptionA(optionA);
        question.setOptionB(optionB);
        question.setOptionC(optionC);
        question.setOptionD(optionD);
        question.setAnswer(answer);
        question.setScore(score);
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
