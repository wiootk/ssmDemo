package com.jun.service;

import com.jun.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User getUserById(int userId);
    List<User> findAll();

    /*name id*/
    List<User> selectDynamic(User record);
    List<User> selectIfTrim(User record);
    List<User> selectChoose(User record);
    /*age id*/
    int updateIfTrim(User record);
    int updateByPrimaryKeySelective(User record);
    /*动态参数*/
    User selectByParms1(User record);
    User selectByParms2(User record);
    User selectByParms3(User record);
    List<User> selectByIdsForeach(Integer[] ids);


}