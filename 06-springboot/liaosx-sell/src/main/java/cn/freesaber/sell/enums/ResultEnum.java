package cn.freesaber.sell.enums;

import lombok.Getter;

/**
 * 异常结果定义
 */
@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETALI_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_PAY_STATUS_ERROR(17, "订单状态不正确"),
    CART_EMPTY(18, "购物车不能为空"),
    WECHAT_MP_ERROR(19, "微信公众方面账号错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(20, "微信支付异步通知金额校验不通过"),
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),
    ORDER_FINISH_SUCCESS(22, "完结订单成功"),
    PRODUCT_STATUS_ERROR(23, "商品状态不正确"),
    PRODUCT_UP_SUCCESS(24, "商品上架成功"),
    PRODUCT_DOWN_SUCCESS(25, "商品下架成功"),
    PRODUCT_ADD_SUCCESS(26, "商品添加成功"),
    PRODUCT_UPDATE_SUCCESS(27, "商品修改成功"),
    CATEGORY_ADD_SUCCESS(28, "类目添加成功"),
    CATEGORY_UPDATE_SUCCESS(29, "类目修改成功")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
