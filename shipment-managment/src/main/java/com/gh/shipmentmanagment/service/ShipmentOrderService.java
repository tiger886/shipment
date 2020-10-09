package com.gh.shipmentmanagment.service;

import com.gh.shipmentmanagment.pojo.ShipmentDetail;
import com.gh.shipmentmanagment.pojo.ShipmentOrder;
import com.gh.shipmentmanagment.service.base.BaseService;
import com.gh.shipmentmanagment.vo.ChangeRootQuantityParamVo;
import com.gh.shipmentmanagment.vo.MergeRequestParamVo;
import com.gh.shipmentmanagment.vo.ShipmentDetailVo;
import com.gh.shipmentmanagment.vo.SplitRequestParamVo;

import java.util.List;

/**
 * @author tiger
 */
public interface ShipmentOrderService extends BaseService<ShipmentOrder> {
    /**
     * create order
     *
     * @param shipmentOrder
     * @return order no
     */
    String createOrder(ShipmentOrder shipmentOrder);

    /**
     * allocate shipments
     *
     * @param shipmentDetailVo
     * @return operate result
     */
    boolean allocateShipment(ShipmentDetailVo shipmentDetailVo);

    /**
     * split shipments
     *
     * @param shipmentDetailVo
     * @return operate result
     */
    boolean splitShipment(SplitRequestParamVo shipmentDetailVo);

    /**
     * merge shipments
     *
     * @param mergeRequestParamVo
     * @return operate result
     */
    boolean mergeShipment(MergeRequestParamVo mergeRequestParamVo);


    /**
     * change root quantity
     *
     * @param changeRootQuantityParamVo
     * @return operate result
     */

    boolean changeRootQuantityShipment(ChangeRootQuantityParamVo changeRootQuantityParamVo);

    /**
     * get ship detail
     *
     * @param orderId
     * @return operate result
     */
    List<ShipmentDetail> getShipDetailByOrderId(long orderId);
}
