package com.dujiaoshou.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;


    private String username;


    private String password;


    private Long createTime;


    private String token;


}