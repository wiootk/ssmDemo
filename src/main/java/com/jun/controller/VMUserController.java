package com.jun.controller;

import com.jun.entity.User;
import com.jun.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/VMuser")
public class VMUserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @RequestMapping("/showAllUser")
    public String showAllUser(HttpServletRequest request, Model model){
        List<User> userList = userService.findAll();
        logger.info("userList's size==============" + userList.size());
        model.addAttribute("userList",userList);
        return "showAllUser";
//        return new ModelAndView("showAllUser", "userList", userList);
    }
}