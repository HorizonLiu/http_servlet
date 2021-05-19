package com.practice.http_servlet.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practice.http_servlet.bean.ResponseBody;
import com.practice.http_servlet.mapper.UserMapper;
import com.practice.http_servlet.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author horizonliu
 * @date 2021/5/18 5:26 下午
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ResponseBody insert() {
        User user = new User();
        user.setUsername("horizonliu");
        user.setPassword("12345678");
        userMapper.insert(user);
        return new ResponseBody();
    }


    public IPage<User> list(int pageIndex, int pageSize) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        User user = new User();
        user.setUsername("horizonliu");
        wrapper.setEntity(user);
        Page<User> page = new Page<>(pageIndex, pageSize);
        return userMapper.selectPage(page, wrapper);
    }

}
