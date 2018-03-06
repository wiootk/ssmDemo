package com.jun.controller;

import com.alibaba.fastjson.JSON;
import com.jun.entity.User;
import com.jun.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FreeMarkerController {
    @RequestMapping("/get/usersInfo")
    public ModelAndView Add(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setName("zhangsan");
        user.setPassword("1234");
        List<User> users = new ArrayList<User>();
        users.add(user);
        return new ModelAndView("usersInfo", "users", users);
    }
    @RequestMapping("/get/allUsers")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = new ArrayList<User>();
        User u1 = new User();
        u1.setName("王五");
        u1.setPassword("123");
        users.add(u1);
        User u2 = new User();
        u2.setName("张三");
        u2.setPassword("2345");
        users.add(u2);
        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("userList", users);
        Map<String, String> product = new HashMap<String, String>();
        rootMap.put("lastProduct", product);
        product.put("url", "http://www.baidu.com");
        product.put("name", "green hose");
        String result = JSON.toJSONString(rootMap);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = JsonUtil.getMapFromJson(result);
        return new ModelAndView("allUsers", "resultMap", resultMap);
    }
}
