package com.gh.shipmentmanagment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @Author: Tiger
 * @Date: 2020/10/10
 */

@ApiModel(description = "修改订单总重量")
@Data
public class ChangeRootQuantityParamVo {
    @ApiModelProperty(value = "待修改的订单编号", example = "1")
    @NotEmpty
    private String orderNo;
    @ApiModelProperty(value = "修改的数量", example = "10")
    @NotNull
    @Min(1)
    private Double tons;
}
