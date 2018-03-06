package com.jun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jun.entity.User;
import com.jun.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pageHelper")
public class PageHelperController {
    @Resource
    private UserService userService;
    @RequestMapping("/getPage")
    @ResponseBody
    public Map<String, Object> getAll(HttpServletRequest request, Model model){
        int pageNum = Integer.valueOf(request.getParameter("pageNum"));
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        boolean isCount=true;
        //获取第pageNum页，pageSize条内容，默认查询总数count
        //PageHelper.startPage(1,0); 不分页
        PageHelper.startPage(pageNum , pageSize,isCount);
        //PageHelper.orderBy("age desc"); 4.0 的方法
        List<User> list =userService.findAll();
        PageInfo page = new PageInfo(list);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page",page);
        map.put("list",list);
        map.put("count",page.getTotal());
        map.put("pagelist",page.getList());
        return  map;
    }
}