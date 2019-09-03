package cn.freesaber.sell.aspect;

import cn.freesaber.sell.constant.CookieConstant;
import cn.freesaber.sell.constant.RedisConstant;
import cn.freesaber.sell.exception.SellerAuthorException;
import cn.freesaber.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SellerAuthorizeAspect {
    private final Logger logger = LoggerFactory.getLogger(SellerAuthorizeAspect.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * cn.freesaber.sell.controller.Seller*.*(..))" +
            "&& !execution(public * cn.freesaber.sell.controller.SellerUserController.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doVertify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            logger.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorException();
        }

        //去redis里查询
        String tokenValue = stringRedisTemplate.opsForValue()
                .get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            logger.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorException();
        }
    }
}
