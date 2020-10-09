package com.gh.shipmentmanagment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * merge a shipment
 *
 * @Author: Tiger
 * @Date: 2020/10/10
 */

@ApiModel(description = "合并物流")
@Data
public class MergeRequestParamVo {

    @ApiModelProperty(value = "合并物流ID" )
    @NotEmpty
    List<Long> mergeShipmentDetailIdList;

}
