package com.xzg.mall.bean.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@TableName("tz_prod_prop")
public class ProdProp implements Serializable {
    private static final long serialVersionUID = -8761177918672000191L;

    /**
     * 属性id
     */
    @TableId
    private Long propId;

    /**
     * 属性名称
     */
    @NotBlank(message = "属性名称不能为空")
    private String propName;

    /**
     * 1:销售属性(规格); 2:参数属性;
     */
    private Integer rule;

    private Long shopId;

    /**
     * 属性值
     */
    @TableField(exist=false)
    @NotEmpty(message="规格属性值不能为空")
    private List<ProdPropValue> prodPropValues;

}
