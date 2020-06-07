package com.dujiaoshou.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dujiaoshou.mapper.UserMapper;
import com.dujiaoshou.model.Result;
import com.dujiaoshou.model.User;
import com.dujiaoshou.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public DataVO<User> findData(Integer page, Integer limit) {

        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<User> userIPage = new Page<>(page, limit);
        IPage<User> result = userMapper.selectPage(userIPage, null);
        List<User> userList = result.getRecords();
        dataVO.setCount((int)result.getTotal());
        dataVO.setData(userList);
        return dataVO;

    }



    public Result regist(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
           wrapper.setEntity(user);
            User existUser = userMapper.selectOne(wrapper);
            if(existUser != null){
                //如果用户名已存在
                result.setMsg("用户名已存在");

            }else{
                userMapper.insert(user);
                //System.out.println(user.getId());
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 登录
     * @param user 用户名和密码
     * @return Result
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.select(user.getUsername());
            User selectOne = userMapper.selectOne(queryWrapper);
            if(selectOne == null){
                result.setMsg("用户名或密码错误");
            }else{
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(user.getId());
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}