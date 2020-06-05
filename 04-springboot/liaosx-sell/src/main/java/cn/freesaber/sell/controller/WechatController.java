package cn.freesaber.sell.controller;

import cn.freesaber.sell.config.ProjectUrlConfig;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
public class WechatController {
    private final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WxMpService wxMpService; // 配置

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // 1.微信用户访问主页projectUrlConfig.getSell()后，判断是否有cookie，如果没有前端重定向到下面的授权连接
    // projectUrlConfig.getSell()+"/wechat/authorize?returnUrl"+首页地址
    // 2.用户点击链接后，会进入到下面的authorize
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        String url = projectUrlConfig.getWechatMpAuthorize() + "/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl, "UTF-8"));
        logger.info("【微信网页授权url】{}", redirectUrl);

        // 3.authorize方法构建一个微信授权redirectUrl，重定向到微信服务器接口
        // 4.微信接口在接受请求后，获取点击操作的用户，将用户信息放入code，将接受参数放入state，然后进入下面的userInfo
        return "redirect:" + redirectUrl;
    }

    // 5.这里就可以拿到用户信息code，以及上面方法传入的参数returnUrl
    @GetMapping("/userInfo")
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
        // 6.重定向到首页，带上openid，就能获取访问应用的用户
        return "redirect:" + returnUrl + "?openid=" + openId;
    }

    @GetMapping("qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        return "";
    }

    @GetMapping("qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        return "";
    }
}
