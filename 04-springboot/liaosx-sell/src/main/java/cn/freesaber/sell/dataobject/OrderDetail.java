package cn.freesaber.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 */
@Entity
@DynamicUpdate
@Data
public class OrderDetail {
    /** 订单详情编号 */
    @Id
    private String detailId;

    /** 订单编号 */
    private String orderId;

    /** 商品编号 */
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品价格 */
    private BigDecimal productPrice;

    /** 商品数量 */
    private int productQuantity;

    /** 商品小图 */
    private String productIcon;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;
}
