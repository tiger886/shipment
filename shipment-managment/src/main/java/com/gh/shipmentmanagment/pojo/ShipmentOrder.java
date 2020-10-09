package com.gh.shipmentmanagment.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Rest api response Util
 *
 * @Author: Tiger
 * @Date: 2020/10/10
 */
@ApiModel(description = "订单")
@Table(name = "shipment_order")
@Data
public class ShipmentOrder implements Serializable {

    private static final long serialVersionUID = 4075568128502305390L;
    @ApiModelProperty(value = "订单编号", example = "1",hidden = true)
    String orderNo;
    @Min(1)
    @ApiModelProperty(value = "总重量-单位吨", example = "13")
    Double totalTons;
    @ApiModelProperty(value = "物流总数量", example = "4")
    @Min(1)
    Integer totalShipments;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", example = "1",hidden = true)
    private Long id;

}
