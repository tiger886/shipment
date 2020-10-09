package com.gh.shipmentmanagment.mapper;

import com.gh.shipmentmanagment.pojo.ShipmentOrder;
import com.gh.shipmentmanagment.template.TemplateMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Rest api response Util
 * @Author: Tiger
 * @Date: 2020/10/10
 */
@Mapper
public interface ShipmentOrderMapper extends TemplateMapper<ShipmentOrder> {

}
