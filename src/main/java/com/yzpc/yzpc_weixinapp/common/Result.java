package com.yzpc.yzpc_weixinapp.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wq
 * @description 统一返回格式
 * @date 2024/12/18 15:38:32
 */
@Data
public class Result{

    private final Integer code;
    private final String msg;

    private final Object data;

    private Result(int code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static Result success(){
        return new Result(0,null,"成功");
    }

    public static Result success(Object data){
        return new Result(1,data,"成功");
    }
    public static Result error(String msg){
        return new Result(0,null,msg);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
