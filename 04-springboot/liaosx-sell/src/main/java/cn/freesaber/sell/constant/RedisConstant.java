package cn.freesaber.sell.constant;

/**
 * redis常量
 */
public interface RedisConstant {
    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 7200; //过期时间
}
