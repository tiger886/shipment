package com.gh.shipmentmanagment.controller;

import com.gh.shipmentmanagment.exception.MessageResult;
import com.gh.shipmentmanagment.exception.MessageResultUtil;
import com.gh.shipmentmanagment.pojo.ShipmentDetail;
import com.gh.shipmentmanagment.pojo.ShipmentOrder;
import com.gh.shipmentmanagment.service.ShipmentOrderService;
import com.gh.shipmentmanagment.vo.ChangeRootQuantityParamVo;
import com.gh.shipmentmanagment.vo.MergeRequestParamVo;
import com.gh.shipmentmanagment.vo.ShipmentDetailVo;
import com.gh.shipmentmanagment.vo.SplitRequestParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * rest api
 * @Author: Tiger
 * @Date: 2020/10/10
 */
@Api(tags = "shipments", description = "shipments api")
@RestController
@RequestMapping("/shipment")
@Slf4j
public class ShipmentController {

    @Autowired
    ShipmentOrderService shipmentOrderService;

    @ApiOperation("create order,return order no")
    @PostMapping("/create")
    public MessageResult<String> createOrder(@RequestBody @Validated ShipmentOrder shipmentOrder) throws Exception {
        String orderNo = shipmentOrderService.createOrder(shipmentOrder);
        return MessageResultUtil.setSuccessResult(orderNo);
    }

    @ApiOperation("allocate shipments")
    @PostMapping("/allocate/shipment")
    public MessageResult<Boolean> allocateShipment(@RequestBody @Validated ShipmentDetailVo shipmentDetailVo) throws Exception {
        boolean isSuccess = shipmentOrderService.allocateShipment(shipmentDetailVo);
        return MessageResultUtil.setSuccessResult(isSuccess);

    }

    @ApiOperation("split shipments")
    @PostMapping("/split/shipment")
    public MessageResult<Boolean> splitShipment(@RequestBody @Validated SplitRequestParamVo shipmentDetailVo) throws Exception {
        boolean isSuccess = shipmentOrderService.splitShipment(shipmentDetailVo);
        return MessageResultUtil.setSuccessResult(isSuccess);
    }

    @ApiOperation("merge shipments")
    @PostMapping("/merge/shipment")
    public MessageResult<Boolean> mergeShipment(@RequestBody @Validated MergeRequestParamVo mergeRequestParamVo) throws Exception {
        boolean isSuccess = shipmentOrderService.mergeShipment(mergeRequestParamVo);
        return MessageResultUtil.setSuccessResult(isSuccess);
    }

    @ApiOperation("change root quantity")
    @PostMapping("/change/root/quantity")
    public MessageResult<Boolean> changeRootQuantityShipment(@RequestBody @Validated ChangeRootQuantityParamVo changeRootQuantityParamVo) throws Exception {
        boolean isSuccess = shipmentOrderService.changeRootQuantityShipment(changeRootQuantityParamVo);
        return MessageResultUtil.setSuccessResult(isSuccess);
    }


    @ApiOperation("query all order")
    @PostMapping("/get/order")
    public MessageResult<List<ShipmentOrder>> getOrder() throws Exception {
        List<ShipmentOrder> shipmentOrderList = shipmentOrderService.selectAll();
        return MessageResultUtil.setSuccessResult(shipmentOrderList);
    }

    @ApiOperation("query  ship detail by order no")
    @GetMapping("/get/{orderId}/shipDetail")
    public MessageResult<List<ShipmentDetail>> getShipDetailByOrderId(@PathVariable long orderId) throws Exception {
        List<ShipmentDetail> shipmentDetailList = shipmentOrderService.getShipDetailByOrderId(orderId);
        return MessageResultUtil.setSuccessResult(shipmentDetailList);
    }
}
