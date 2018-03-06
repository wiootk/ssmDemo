package com.jun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FreeMarkerDemoController {
    @RequestMapping("/freeMarkerDemo")
    public ModelAndView Add(HttpServletRequest request, HttpServletResponse response, Model model) {

        //1.简单字符串
        model.addAttribute("textDemo","HelloWorld!!");
        //2.计算||条件判断||输出特殊字符
        int a=1;
        int b=2;
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        //3.遍历List
        List<String> list=new ArrayList<String>();
        list.add("早餐");
        list.add("中餐");
        list.add("晚餐");
        model.addAttribute("list",list);
        //4.遍历map
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("key1","value1");
        map.put("key2","value2");
        model.addAttribute("map",map);
        //5.遍历复杂map
        Map<String,Object>userInfo=new HashMap<String,Object>();
        userInfo.put("username","username");
        Map<String,Object>cMap=new HashMap<String,Object>();
        cMap.put("cKey1","cValue1");
        cMap.put("cKey2","cValue2");
        userInfo.put("cMap",cMap);
        model.addAttribute("userinfo",userInfo);
        //6.遍历嵌套map
        Map<String,Object>outerMap=new HashMap<String,Object>();
        Map<String,String>innerMap=new HashMap<String,String>();
        innerMap.put("innerKey1","innerValue1");
        innerMap.put("innerKey2","innerValue2");
        outerMap.put("key1","value1");
        outerMap.put("key2","value2");
        outerMap.put("innerMap",innerMap);
        model.addAttribute("outerMap",outerMap);
        //7.Map、List嵌套
        Map<String,Object>mMap=new HashMap<String,Object>();
        List<String>innerList=new ArrayList<String>();
        innerList.add("吃早餐");
        innerList.add("吃中餐");
        innerList.add("吃晚餐");
        mMap.put("key1","value1");
        mMap.put("key2","value2");
        mMap.put("innerList",innerList);
        model.addAttribute("mMap",mMap);
        return new ModelAndView("freeMarkerDemo");
    }
}