package cn.freesaber.sell.exception;

import cn.freesaber.sell.enums.ResultEnum;
import lombok.Getter;

@Getter
public class SellException extends RuntimeException {
    /** 异常返回编码 */
    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
