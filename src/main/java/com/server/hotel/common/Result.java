package com.server.hotel.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;//成功：200，失败：0
    private String msg;
    private T data;

    public final static int SUCCESSFUL = 200;
    public final static int FAILED = 0;

    public static <T> Result<T> success(String msg, T data) {
        Result<T> r = new Result<>();
        r.data = data;
        r.msg = msg;
        r.code = SUCCESSFUL;
        return r;
    }

    public static <T> Result<T> fail(String msg, int... code) {
        Result<T> r = new Result<>();
        if (code.length > 0) {
            r.code = code[0];
        } else {
            r.code = FAILED;
        }
        r.msg = msg;
        return r;
    }

}
