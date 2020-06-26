package com.dujiaoshou.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dujiaoshou.model.Question;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionMapper extends BaseMapper<Question> {



    @Select("select * from question")
    List<Question> selectQuestionList(IPage<Question> page);

}