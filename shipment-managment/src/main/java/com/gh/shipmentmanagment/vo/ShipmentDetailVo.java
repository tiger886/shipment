package com.gh.shipmentmanagment.vo;

import com.gh.shipmentmanagment.pojo.ShipmentDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * @Author: Tiger
 * @Date: 2020/10/10
 */

@ApiModel(description = "分配物流参数")
@Data
public class ShipmentDetailVo {
    @ApiModelProperty(value = "物流详情")
    List<ShipmentDetail> shipmentDetailList;
    @ApiModelProperty(value = "订单编号", example = "1")
    @NotEmpty
    private String orderNo;
}
