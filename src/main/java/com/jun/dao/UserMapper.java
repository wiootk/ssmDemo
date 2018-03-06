package com.jun.dao;

import com.jun.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);



    int updateByPrimaryKey(User record);

    List<User> findAll();



    /*name id*/
    List<User> selectDynamic(User record);
    List<User> selectIfTrim(User record);
    List<User> selectChoose(User record);
    List<User> selectByIdsForeach(Integer[] ids);


    /*age id*/
    int updateIfTrim(User record);
    int updateByPrimaryKeySelective(User record);
    /*动态参数*/
    User selectByParms1(String name,Integer age);
    User selectByParms2(Map paramMap);
    User selectByParms3(@Param("name")String name,@Param("age")Integer age);










}