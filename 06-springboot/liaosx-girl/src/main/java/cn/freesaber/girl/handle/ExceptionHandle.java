package cn.freesaber.girl.handle;

import cn.freesaber.girl.Exception.GirlException;
import cn.freesaber.girl.domain.Girl;
import cn.freesaber.girl.domain.Result;
import cn.freesaber.girl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        }

        logger.error("【系统异常】{}", e);
        return ResultUtil.error(-1, "未知错误");
    }
}
