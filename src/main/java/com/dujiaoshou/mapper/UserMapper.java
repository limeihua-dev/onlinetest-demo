package com.dujiaoshou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dujiaoshou.model.User;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserMapper extends BaseMapper<User> {


}