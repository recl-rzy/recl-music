package com.ren.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 统一结果返回
 * @ClassName: Result
 * @Project_Name: recl
 * @Author RZY
 * @Date: 2021/7/11 21:59
 * @Vertion: 2019.1
 */

@Data
public class Result {

    @ApiModelProperty(value = "结果成功状态")
    private Boolean success;

    @ApiModelProperty(value = "返回状态码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回类型")
    private String type;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //构造方法私有化
    private Result() {
    }

    public static Result ok() {

        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        result.setType("success");
        return result;
    }

    public static Result error() {

        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setType("error");
        result.setMessage("失败");
        return result;
    }

    public Result success(Boolean success) {

        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {

        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {

        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value) {

        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {

        this.setData(map);
        return this;
    }


}

