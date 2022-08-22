package com.example.springboot.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class RestBean<T> {
    int code;
    String reason;
    T data;
    public RestBean(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}
