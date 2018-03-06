package com.jun.service.impl;

import com.jun.dao.UserMapper;
import com.jun.entity.User;
import com.jun.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userDao;

    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }

    public List<User> findAll() {
        return this.userDao.findAll();
    }

    public List<User> selectDynamic(User record) {
        return this.userDao.selectDynamic(record);
    }
    public List<User> selectIfTrim(User record) {
        return this.userDao.selectIfTrim(record);
    }
    public List<User> selectChoose(User record) {
        return this.userDao.selectChoose(record);
    }
    public int updateIfTrim(User record) {
        return this.userDao.updateIfTrim(record);
    }
    public int updateByPrimaryKeySelective(User record) {
        return this.userDao.updateByPrimaryKeySelective(record);
    }
    public User selectByParms1(User record) {
        return this.userDao.selectByParms1(record.getName(), record.getAge());
    }
    public User selectByParms2(User record) {
        Map paramMap = new HashMap();
        paramMap.put("name", record.getName());
        paramMap.put("age", record.getAge());
        return this.userDao.selectByParms2(paramMap);
    }
    public User selectByParms3(User record) {
        return this.userDao.selectByParms3(record.getName(), record.getAge());
    }
    public List<User> selectByIdsForeach(Integer[] ids) {
        return this.userDao.selectByIdsForeach(ids);
    }

    ;
}
