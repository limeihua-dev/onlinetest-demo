package com.dujiaoshou;

import com.dujiaoshou.mapper.QuestionMapper;
import com.dujiaoshou.mapper.UserMapper;
import com.dujiaoshou.model.Question;
import com.dujiaoshou.model.User;
import com.dujiaoshou.service.QuestionService;
import com.dujiaoshou.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Test
    void contextLoads() {
//        QuestionExample questionExample = new QuestionExample();
//        questionExample.createCriteria()
//                .andIdIsNotNull();
//        List<Question> questions = questionMapper.selectByExample(questionExample);
//
//        System.out.println(questions);

        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    void finData() {
//        DataVO dataVO = questionService.findData();
        int i = 0;
    }

    @Test
    void randonId() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(100));//能产生一个大于或等于0，小于但不等于10的随机整数，也就是个位数
        }

    }

    @Test
    void random() {
        System.out.print("输入产生的随机数范围，1到N，N=");
        int n = 10;
        int randArr[] = new int[n];
        int i = 0;
        ArrayList<Question> questions = new ArrayList<>();
        while (i < n) {
            int rand = (new Random().nextInt(n) + 1);
            boolean isRandExist = false;
            for (int j = 0; j < randArr.length; j++) {
                if (randArr[j] == rand) {
                    isRandExist = true;
                    break;
                }
            }
            if (isRandExist == false) {
                randArr[i] = rand;
                i++;
                Question question = questionMapper.selectById(rand);
                questions.add(question);
            }
        }

        System.out.println("questions============"+questions);


    }
}








