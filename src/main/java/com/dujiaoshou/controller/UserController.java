package com.dujiaoshou.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujiaoshou.mapper.UserMapper;
import com.dujiaoshou.model.User;
import com.dujiaoshou.service.UserService;
import com.dujiaoshou.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Transactional
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

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





    @GetMapping("/login")
    public String login(HttpServletRequest request){
        if (request.getSession().getAttribute("user") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam(name = "username") String username,
                          @RequestParam(name = "password") String password,
                          Model model,
                          HttpServletRequest request){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select(username);
        User dbUser = userMapper.selectOne(wrapper);
        if (dbUser == null) {
            model.addAttribute("msg", "用户不存在");
            return "login";
        }
        if (!dbUser.getPassword().equals(password)) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
        request.getSession().setAttribute("user", username);
        return "redirect:/";
    }



    //用户注册
    @GetMapping("/register")
    public String register(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "repassword") String repassword,
                             Model model) {
        if (!password.equals(repassword)) {
            model.addAttribute("msg", "密码输入不一致");
            return "register";
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User dbUser = userMapper.selectOne(wrapper);
        System.out.println("====================="+dbUser);
        if (dbUser != null) {
            model.addAttribute("msg", "用户已存在");
            return "register";

        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setCreateTime(System.currentTimeMillis());
        userMapper.insert(newUser);
        return "redirect:/";
    }
}



