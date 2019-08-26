package cn.freesaber.sell.dto;

import lombok.Data;

@Data
public class CartDTO {
    /** 商品Id */
    private String productId;

    /** 库存操作数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
