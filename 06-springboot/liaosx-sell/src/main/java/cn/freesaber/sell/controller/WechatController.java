package cn.freesaber.sell.controller;

import cn.freesaber.sell.enums.ResultEnum;
import cn.freesaber.sell.exception.SellException;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;

@Controller
//@RequestMapping("/wechat")
public class WechatController {
    private final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WxMpService wxMpService; // 1.配置

    @GetMapping("/")
    @ResponseBody
    public String index(@RequestParam("echostr") String echostr){
        logger.info("echostr:{}",echostr);
        return  echostr;
    }

    @GetMapping("/wechat/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //2. 调用方法
        String url = "http://freesaber.natapp1.cc/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, null);
        logger.info("【微信网页授权url】{}", redirectUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/wechat/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            logger.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
