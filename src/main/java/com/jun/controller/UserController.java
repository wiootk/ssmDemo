package com.jun.controller;

import com.jun.entity.User;
import com.jun.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }


    /*name id*/
    @RequestMapping("/selectDynamic")
    @ResponseBody
    public List<User> selectDynamic(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        List<User> list = this.userService.selectDynamic(user);
        return list;
    }

    @RequestMapping("/selectIfTrim")
    @ResponseBody
    public List<User> selectIfTrim(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        List<User> list = this.userService.selectIfTrim(user);
        return list;
    }

    @RequestMapping("/selectChoose")
    @ResponseBody
    public List<User> selectChoose(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        List<User> list = this.userService.selectChoose(user);
        return list;
    }

    /*age id*/
    @RequestMapping("/updateIfTrim")
    @ResponseBody
    public int updateIfTrim(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setId(Integer.parseInt(request.getParameter("age")));
        }
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        int result = this.userService.updateIfTrim(user);
        return result;
    }

    @RequestMapping("/updateByPrimaryKeySelective")
    @ResponseBody
    public int updateByPrimaryKeySelective(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setId(Integer.parseInt(request.getParameter("age")));
        }
        if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
            user.setId(Integer.parseInt(request.getParameter("id")));
        }
        int result = this.userService.updateByPrimaryKeySelective(user);
        return result;
    }

    /*动态参数 name age*/
    @RequestMapping("/selectByParms1")
    @ResponseBody
    public User selectByParms1(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setAge(Integer.parseInt(request.getParameter("age")));
        }
        User result = this.userService.selectByParms1(user);
        return result;
    }

    @RequestMapping("/selectByParms2")
    @ResponseBody
    public User selectByParms2(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setAge(Integer.parseInt(request.getParameter("age")));
        }
        User result = this.userService.selectByParms2(user);
        return result;
    }

    @RequestMapping("/selectByParms3")
    @ResponseBody
    public User selectByParms3(HttpServletRequest request, Model model) {
        User user = new User();
        user.setName(request.getParameter("name"));
        if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
            user.setAge(Integer.parseInt(request.getParameter("age")));
        }
        User result = this.userService.selectByParms3(user);
        return result;
    }

    @RequestMapping("/selectByIdsForeach")
    @ResponseBody
    public List<User> selectByIdsForeach(Integer[] ids) {
        Integer[] idss = new Integer[]{2, 3};
        return this.userService.selectByIdsForeach(idss);
    }


}