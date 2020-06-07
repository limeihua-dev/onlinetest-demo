package com.dujiaoshou.model;

import lombok.Data;

@Data
public class Question {

    private Long id;


    private String title;


    private String optionA;


    private String optionB;

    private String optionD;


    private String optionC;

    private Integer score;


    private String answer;

    private  Long gmtCreate;

    private Long gmtModified;


}