package com.gh.shipmentmanagment.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 *  Rest api response Util
 * @Author: Tiger
 * @Date: 2020/10/10
 */
@ApiModel(description = "订单物流")
@Table(name = "shipment_detail")
@Data
public class ShipmentDetail implements Serializable {

    @ApiModelProperty(value = "记录是否有效，1有效0无效", example = "1",hidden = true)
    Short status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", example = "1",hidden = true)
    private Long id;
    @ApiModelProperty(value = "订单表ID", example = "1",hidden = true)
    private Long orderId;
    @Min(1)
    @ApiModelProperty(value = "物流重量-单位吨", example = "12")
    private Double shipTons;
    @ApiModelProperty(value = "变更记录 0 初始", example = "0",hidden = true)
    private Integer version;
    @ApiModelProperty(value = "变更行为 0 初始, 1 split  ,2 merge,3 change quantity", example = "0",hidden = true)
    private Integer action;

}
