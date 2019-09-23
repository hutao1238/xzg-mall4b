package com.xzg.mall.bean.app.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author hutao
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderItemDto extends ProductItemDto implements Serializable {

}
