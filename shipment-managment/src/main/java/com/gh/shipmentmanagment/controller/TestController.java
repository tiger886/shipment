/**
 * Demon
 */

package com.gh.shipmentmanagment.controller;


import com.gh.shipmentmanagment.exception.ErrorCodeEnum;
import com.gh.shipmentmanagment.exception.MessageResult;
import com.gh.shipmentmanagment.exception.MessageResultUtil;
import com.gh.shipmentmanagment.pojo.ShipmentOrder;
import com.gh.shipmentmanagment.service.ShipmentOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * web api test
 * @Author: Tiger
 * @Date: 2020/10/10
 */

@Api(tags = "api demon", description = "RestFul功能测试")
@RestController
@RequestMapping("/test/api")
@Slf4j
public class TestController {
    @Autowired
    ShipmentOrderService shipmentOrderService;

    @ApiOperation("web功能测试-查询")
    @PostMapping("/query_userinfo_by_name/{name}")
    public MessageResult<String> queryUserInfoByName(@PathVariable(value = "name") String name) throws Exception {
        log.info("======name:{}", name);
        ShipmentOrder order = new ShipmentOrder();
        order.setOrderNo("11111111");
        order.setTotalShipments(111);
        order.setTotalTons((double) 1222);
        shipmentOrderService.add(order);
        if (StringUtils.trimAllWhitespace(name).length() > 0) {

            return MessageResultUtil.setSuccessResult("cgh");

        } else {
            return MessageResultUtil.setErrorResult(ErrorCodeEnum.UNKONW_ERROR);
        }
    }


}
