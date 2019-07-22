package cn.freesaber.girl.utils;

import cn.freesaber.girl.domain.Result;

public class ResultUtil {
    // 请求成功返回结果
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);

        return result;
    }

    public static Result success() {
        return success(null);
    }

    // 请求失败返回结果
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);

        return result;
    }
}
