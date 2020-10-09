package com.gh.shipmentmanagment.service.impl;

import com.gh.shipmentmanagment.constant.ShipOrderConstant;
import com.gh.shipmentmanagment.exception.ErrorCodeEnum;
import com.gh.shipmentmanagment.exception.TemplateException;
import com.gh.shipmentmanagment.mapper.ShipmentOrderMapper;
import com.gh.shipmentmanagment.pojo.ShipmentDetail;
import com.gh.shipmentmanagment.pojo.ShipmentOrder;
import com.gh.shipmentmanagment.service.ShipmentDetailService;
import com.gh.shipmentmanagment.service.ShipmentOrderService;
import com.gh.shipmentmanagment.service.base.impl.BaseServiceImpl;
import com.gh.shipmentmanagment.util.DoubleUtil;
import com.gh.shipmentmanagment.util.OrderUtil;
import com.gh.shipmentmanagment.vo.ChangeRootQuantityParamVo;
import com.gh.shipmentmanagment.vo.MergeRequestParamVo;
import com.gh.shipmentmanagment.vo.ShipmentDetailVo;
import com.gh.shipmentmanagment.vo.SplitRequestParamVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * service
 *
 * @Author: Tiger
 * @Date: 2020/10/10
 */

@Slf4j
@Service
public class ShipmentOrderServiceImpl extends BaseServiceImpl<ShipmentOrderMapper, ShipmentOrder> implements ShipmentOrderService {

    @Autowired
    ShipmentDetailService shipmentDetailService;

    /**
     * create order
     *
     * @param shipmentOrder
     * @return order no
     */
    @Override
    public String createOrder(ShipmentOrder shipmentOrder) {
        String orderNo = OrderUtil.createOrderNo();
        shipmentOrder.setOrderNo(orderNo);
        Long orderId = this.add(shipmentOrder);
        log.info("createOrder orderNo:{},orderID:{}", orderNo, orderId);
        return orderNo;
    }

    /**
     * allocate shipments
     *
     * @param shipmentDetailVo
     * @return operate result
     */
    @Override
    public boolean allocateShipment(ShipmentDetailVo shipmentDetailVo) {
        //check order no if exist
        ShipmentOrder shipmentOrderQuery = new ShipmentOrder();
        shipmentOrderQuery.setOrderNo(shipmentDetailVo.getOrderNo());
        List<ShipmentOrder> shipmentOrders = select(shipmentOrderQuery);
        if (shipmentOrders.size() <= 0) {
            throw new TemplateException(ErrorCodeEnum.ORDER_NOT_EXITS);
        }
        shipmentOrderQuery = shipmentOrders.get(0);

        //check if shipment detail exist
        List<ShipmentDetail> shipmentDetailListQuery = this.getShipDetailByOrderId(shipmentOrderQuery.getId());
        if (shipmentDetailListQuery != null && shipmentDetailListQuery.size() > 0) {
            throw new TemplateException(ErrorCodeEnum.SHIPMENT_HAS_EXITS);
        }
        //valid total tons
        double tons = 0;
        for (ShipmentDetail shipmentDetail : shipmentDetailVo.getShipmentDetailList()) {
            tons = DoubleUtil.add(tons, shipmentDetail.getShipTons());
        }
        if (tons != shipmentOrderQuery.getTotalTons()) {
            throw new TemplateException(ErrorCodeEnum.ORDER_TON_VALID_ERROR);
        }
        // write to db
        for (ShipmentDetail shipmentDetail : shipmentDetailVo.getShipmentDetailList()) {
            shipmentDetail.setOrderId(shipmentOrderQuery.getId());
            shipmentDetail.setAction(ShipOrderConstant.ACTION_INIT);
            shipmentDetail.setVersion(0);
            shipmentDetail.setStatus((short) ShipOrderConstant.STATUS_ENABLE);
        }
        long result = shipmentDetailService.batchAdd(shipmentDetailVo.getShipmentDetailList());
        log.info("allocateShipment db result:{}", result);
        return true;
    }

    /**
     * split shipments
     *
     * @param shipmentDetailVo
     * @return operate result
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean splitShipment(SplitRequestParamVo shipmentDetailVo) {
        log.info("splitShipment shipmentDetail id:{}", shipmentDetailVo.getSplitShipmentDetailId());
        //check shipmentDetail ID if exist
        ShipmentDetail shipmentDetailQuery = shipmentDetailService.get(shipmentDetailVo.getSplitShipmentDetailId());
        if (shipmentDetailQuery == null) {
            throw new TemplateException(ErrorCodeEnum.SHIPMENT_ID_NOT_EXITS);
        }
        //check split shipment tons
        double tons = 0;
        for (ShipmentDetail shipmentDetail : shipmentDetailVo.getShipmentDetailList()) {
            tons = DoubleUtil.add(tons, shipmentDetail.getShipTons());
        }
        if (tons != shipmentDetailQuery.getShipTons()) {
            throw new TemplateException(ErrorCodeEnum.SHIPMENT_SPLIT_TON_VALID_ERROR);
        }
        //disable splited shipment detail
        shipmentDetailQuery.setStatus((short) ShipOrderConstant.STATUS_DISABLE);
        shipmentDetailService.update(shipmentDetailQuery);
        // save new split shipment
        for (ShipmentDetail shipmentDetail : shipmentDetailVo.getShipmentDetailList()) {
            shipmentDetail.setStatus((short) ShipOrderConstant.STATUS_ENABLE);
            shipmentDetail.setAction(ShipOrderConstant.ACTION_SPLIT);
            shipmentDetail.setOrderId(shipmentDetailQuery.getOrderId());
            shipmentDetail.setVersion(shipmentDetailQuery.getVersion() + 1);
        }
        shipmentDetailService.batchAdd(shipmentDetailVo.getShipmentDetailList());
        return true;
    }

    /**
     * merge shipments
     *
     * @param mergeRequestParamVo
     * @return operate result
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean mergeShipment(MergeRequestParamVo mergeRequestParamVo) {
        //check merge id,change  merge status,calc tons
        List<ShipmentDetail> shipmentDetailListQuery = new ArrayList<ShipmentDetail>();
        double tons = 0;
        for (Long mergeID : mergeRequestParamVo.getMergeShipmentDetailIdList()) {
            ShipmentDetail shipmentDetail = shipmentDetailService.get(mergeID);
            if (shipmentDetail == null) {
                throw new TemplateException(ErrorCodeEnum.SHIPMENT_MERGE_ID_VALID_ERROR);
            }
            shipmentDetail.setStatus((short) ShipOrderConstant.STATUS_DISABLE);
            shipmentDetailService.update(shipmentDetail);
            shipmentDetailListQuery.add(shipmentDetail);
            tons = DoubleUtil.add(tons, shipmentDetail.getShipTons());
        }
        //add new shipment
        ShipmentDetail shipmentDetailInsert = new ShipmentDetail();
        shipmentDetailInsert.setStatus((short) ShipOrderConstant.STATUS_ENABLE);
        shipmentDetailInsert.setVersion(shipmentDetailListQuery.get(0).getVersion() + 1);
        shipmentDetailInsert.setOrderId(shipmentDetailListQuery.get(0).getOrderId());
        shipmentDetailInsert.setAction(ShipOrderConstant.ACTION_MERGE);
        shipmentDetailInsert.setShipTons(tons);
        long result = shipmentDetailService.add(shipmentDetailInsert);
        return result > 0;
    }

    /**
     * change root quantity
     *
     * @param changeRootQuantityParamVo
     * @return operate result
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean changeRootQuantityShipment(ChangeRootQuantityParamVo changeRootQuantityParamVo) {
        //check order no if exist
        ShipmentOrder shipmentOrderQuery = new ShipmentOrder();
        shipmentOrderQuery.setOrderNo(changeRootQuantityParamVo.getOrderNo());
        List<ShipmentOrder> shipmentOrders = select(shipmentOrderQuery);
        if (shipmentOrders.size() <= 0) {
            throw new TemplateException(ErrorCodeEnum.ORDER_NOT_EXITS);
        }
        shipmentOrderQuery = shipmentOrders.get(0);
        //calc subtract result
        double totalTon = shipmentOrderQuery.getTotalTons();
        double subResult = DoubleUtil.sub(changeRootQuantityParamVo.getTons(), shipmentOrderQuery.getTotalTons());
        //update order
        shipmentOrderQuery.setTotalTons(changeRootQuantityParamVo.getTons());
        this.update(shipmentOrderQuery);
        //query shipment detail
        ShipmentDetail shipmentDetailQuery = new ShipmentDetail();
        shipmentDetailQuery.setOrderId(shipmentOrderQuery.getId());
        List<ShipmentDetail> shipmentDetailListQuery = shipmentDetailService.select(shipmentDetailQuery);
        // adjust shipment detail and save
        List<ShipmentDetail> shipmentDetailListAdjust = new ArrayList<>();
        for (ShipmentDetail ship : shipmentDetailListQuery) {
            ship.setStatus((short) ShipOrderConstant.STATUS_DISABLE);
            //create new shipment detail
            ShipmentDetail shipmentDetailAdjust = new ShipmentDetail();
            shipmentDetailAdjust.setStatus((short) ShipOrderConstant.STATUS_ENABLE);
            shipmentDetailAdjust.setOrderId(ship.getOrderId());
            shipmentDetailAdjust.setVersion(ship.getVersion() + 1);
            shipmentDetailAdjust.setAction(ShipOrderConstant.ACTION_ROOT_CHANGE);
            //calc new ton  a+a*diff/total
            try {
                double shipmentDetailTon = ship.getShipTons();
                double increaseTon = DoubleUtil.divide(DoubleUtil.mul(shipmentDetailTon, subResult), totalTon, true);
                double calcResult = DoubleUtil.add(shipmentDetailTon, increaseTon);
                shipmentDetailAdjust.setShipTons(calcResult);
            } catch (ArithmeticException e) {
                throw new TemplateException(ErrorCodeEnum.ORDER_TON_ADJUST_ERROR);
            }
            shipmentDetailListAdjust.add(shipmentDetailAdjust);
            //update older record
            shipmentDetailService.update(ship);
        }
        // save new shipment to db
        shipmentDetailService.batchAdd(shipmentDetailListAdjust);

        return true;
    }

    /**
     * get ship detail
     *
     * @param orderId
     * @return operate result
     */
    @Override
    public List<ShipmentDetail> getShipDetailByOrderId(long orderId) {
        ShipmentDetail shipmentDetail = new ShipmentDetail();
        shipmentDetail.setOrderId(orderId);
        shipmentDetail.setStatus((short) ShipOrderConstant.STATUS_ENABLE);
        return shipmentDetailService.select(shipmentDetail);
    }
}
