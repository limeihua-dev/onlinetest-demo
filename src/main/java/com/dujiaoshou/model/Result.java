package com.dujiaoshou.model;

import lombok.Data;

@Data
public class Result<T> {

    private boolean success;
    private String msg;
    private T detail;
}
