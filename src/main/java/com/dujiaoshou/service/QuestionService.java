package com.dujiaoshou.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dujiaoshou.exception.CustomizeErrorCode;
import com.dujiaoshou.exception.CustomizeException;
import com.dujiaoshou.mapper.QuestionMapper;
import com.dujiaoshou.model.Question;
import com.dujiaoshou.vo.DataVO;
import com.dujiaoshou.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;


    public DataVO<Question> findData(Integer page, Integer limit) {


        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Question> questiontIPage = new Page<>(page, limit);
        IPage<Question> result = questionMapper.selectPage(questiontIPage, null);
        List<Question> questionList = result.getRecords();
        dataVO.setCount((int) result.getTotal());


        dataVO.setData(questionList);
        return dataVO;

    }


    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            // 创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            // 更新
            Question dbQuestion = questionMapper.selectById(question.getId());
            if (dbQuestion == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }


            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setOptionA(question.getOptionA());
            updateQuestion.setOptionB(question.getOptionB());
            updateQuestion.setOptionC(question.getOptionC());
            updateQuestion.setOptionD(question.getOptionD());
            updateQuestion.setAnswer(question.getAnswer());
            updateQuestion.setScore(question.getScore());


            QueryWrapper<Question> updateWrapper = new QueryWrapper<>();
            updateWrapper.eq("id", question.getId());
            int updated = questionMapper.update(question, updateWrapper);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }


    public PaginationVO list(Integer page,Integer limit) {

        PaginationVO paginationVO = new PaginationVO();

        IPage<Question> questiontIPage = new Page<>(page, limit);
        IPage<Question> result = questionMapper.selectPage(questiontIPage, null);

        //计算总分页数
       Integer totalPage = (int)result.getPages();


        List<Question> questionList = result.getRecords();

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationVO.setPagination(totalPage,page);
        paginationVO.setData(questionList);
        return paginationVO;
        //计算随机Id、以便让题目随机抽取
//        System.out.print("输入产生的随机数范围，1到N，N=");
//        int n = 10;
//        int randArr[] = new int[n];
//        int i = 0;
//        ArrayList<Question> questions = new ArrayList<>();
//        while (i < n) {
//            int rand = (new Random().nextInt(n) + 1);
//            boolean isRandExist = false;
//            for (int j = 0; j < randArr.length; j++) {
//                if (randArr[j] == rand) {
//                    isRandExist = true;
//                    break;
//                }
//            }
//            if (isRandExist == false) {
//                randArr[i] = rand;
//                i++;
//                Question question = questionMapper.selectById(rand);
//                questions.add(question);
//            }
//        }
//        System.out.println("questions============" + questions);



    }
}