package com.gh.shipmentmanagment.vo;

import com.gh.shipmentmanagment.pojo.ShipmentDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @Author: Tiger
 * @Date: 2020/10/10
 */

@ApiModel(description = "拆分物流")
@Data
public class SplitRequestParamVo {
    @ApiModelProperty(value = "拆分物流ID")
    @NotNull
    private Long splitShipmentDetailId;
    @ApiModelProperty(value = "拆分后物流详情")
    @NotEmpty
    List<ShipmentDetail> shipmentDetailList;

}
