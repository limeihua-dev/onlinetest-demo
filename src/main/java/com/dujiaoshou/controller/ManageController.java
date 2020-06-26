package com.dujiaoshou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujiaoshou.mapper.ManagerMapper;
import com.dujiaoshou.mapper.UserMapper;
import com.dujiaoshou.model.Manager;
import com.dujiaoshou.model.User;
import com.dujiaoshou.service.QuestionService;
import com.dujiaoshou.service.UserService;
import com.dujiaoshou.vo.DataVO;
import com.dujiaoshou.vo.UserGradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManageController {


    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    //用户页面列表
    @RequestMapping("/uList")
    @ResponseBody
    public DataVO userList(Integer page, Integer limit) {

        DataVO<User> dataVO = userService.findData(page, limit);
        return dataVO ;
    }


    @GetMapping("/userList")
    public String redirect(){
        return "userList";
    }


    /*
    * 管理员登录
    * */

    @GetMapping("/manager")
    public String login(HttpServletRequest request){
        return "adminLogin";
    }

    @PostMapping("/aLogin")
    public String doLogin(@RequestParam(name = "username") String username,
                          @RequestParam(name = "password") String password,
                          Model model,
                          HttpServletRequest request){
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Manager dbmanager = managerMapper.selectOne(wrapper);
        if (dbmanager == null) {
            model.addAttribute("msg", "管理员不存在");
            return "login";
        }
        if (!dbmanager.getPassword().equals(password)) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
        request.getSession().setAttribute("user", username);
        return "redirect:/managerData";
    }



    /**
     * 后台管理--用户管理--试题管理--成绩管理
     */



    @GetMapping("/managerData")
    public String Data(){
        return "managerData";
    }








    //管理员后台查询学生成绩
    @RequestMapping("/grade")
    @ResponseBody
    public UserGradeVO getGrade(){
        return userService.getGradeVO();
    }

    @GetMapping("/grades")
    public String grades(){
        return "echarts";
    }





}