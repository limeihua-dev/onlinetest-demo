package com.dujiaoshou.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserGradeVO {
    private List<String> usernames;
    private List<Integer> grades;
}
