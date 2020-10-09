create table shipment_order (
    id BIGINT(20) IDENTITY  PRIMARY KEY  AUTO_INCREMENT COMMENT '主键自增',
    order_no  varchar(14) COMMENT '订单号',
    total_tons  DECIMAL (10,2)   COMMENT '总重量-单位吨',
    total_shipments  int COMMENT '物流总数量',
    create_time  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP
);
create table shipment_detail (
    id BIGINT(20) PRIMARY KEY  AUTO_INCREMENT COMMENT '主键自增',
    order_id  BIGINT(20) COMMENT '订单表ID',
    ship_tons  DECIMAL (10,2)   COMMENT '物流重量-单位吨',
    status   smallint DEFAULT 1 COMMENT '记录是否有效，1有效0无效',
    version INT   DEFAULT 0  COMMENT '变更记录 0 初始',
    action  INT   DEFAULT 0  COMMENT '变更行为 0 初始, 1 split  ,2 merge,3 change quantity',
    create_time  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP
);
COMMIT;
