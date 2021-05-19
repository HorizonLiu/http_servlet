package com.practice.http_servlet.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practice.http_servlet.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author horizonliu
 * @date 2021/5/18 5:24 下午
 */
@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页条件查询
     *
     * @param page 分页条件
     * @param queryWrapper 查询条件
     * @return 查询结果
     */
    IPage<User> selectPage(Page<User> page, @Param(Constants.WRAPPER) QueryWrapper<User> queryWrapper);
}
