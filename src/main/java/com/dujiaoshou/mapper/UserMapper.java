package com.dujiaoshou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dujiaoshou.model.User;

import java.util.List;

import com.dujiaoshou.vo.UserGradeBarVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

public interface UserMapper extends BaseMapper<User> {


    @Select("select username,grade from user;")
    List<UserGradeBarVO> findAllGradeBarVO();

    @Select("select grade from user")
    List<Integer> findAllGrade();


}