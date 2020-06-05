package cn.freesaber.sell.handler;

import cn.freesaber.sell.VO.ResultVO;
import cn.freesaber.sell.config.ProjectUrlConfig;
import cn.freesaber.sell.exception.SellException;
import cn.freesaber.sell.exception.SellerAuthorException;
import cn.freesaber.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorException.class)
    public ModelAndView handlerAuthorizeException() {
//        return new ModelAndView("redirect:"
//         .concat(projectUrlConfig.getWechatOpenAuthorize())
//         .concat("/wechat/arAuthorize")
//         .concat("?returnUrl=")
//        .concat(projectUrlConfig.getSell())
//        .concat("/seller/login"));

        // 由于没有微信企业号，这里直接写死openid，和数据库中记录对应
        // 前端没有登录cookie的情况下访问seller/seller，看请求过程：第一次访问被重定向到/seller/login?openid=123
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getSell())
                .concat("/seller/login?openid=123456")
        );
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handleSellException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

//    @ResponseStatus(HttpStatus.FORBIDDEN)

}
