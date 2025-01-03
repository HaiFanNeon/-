package com.example.stusystem.common;


import lombok.Data;

@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T d) {
        Result<T> result = new Result<T>();
        result.setData(d);
        result.code = 1;
        return result;
    }


    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<T>();
        result.setMsg(msg);
        result.code = 0;
        return result;
    }

}
