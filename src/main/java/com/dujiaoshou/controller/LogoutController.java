package com.dujiaoshou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            request.getSession().removeAttribute("user");
        }
        return "redirect:/";
    }
}