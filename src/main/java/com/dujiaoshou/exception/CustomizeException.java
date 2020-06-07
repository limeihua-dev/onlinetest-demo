package com.dujiaoshou.exception;

/*
继承RunTimeException，是为了其他的程序段不会try，catch，异常抛出，都在这里解决
* */
public class CustomizeException  extends  RuntimeException{

    private String message;
    private Integer code;
    public  CustomizeException(ICustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
    @Override
    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
