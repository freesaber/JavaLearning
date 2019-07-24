package cn.freesaber.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /**  类目Id **/
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**  类目名字 **/
    private String categoryName;

    /**  类目编号 **/
    private Integer categoryType;

    /**  创建世界 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;
}
