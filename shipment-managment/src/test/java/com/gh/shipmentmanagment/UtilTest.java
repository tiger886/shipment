package com.gh.shipmentmanagment;

import com.gh.shipmentmanagment.util.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UtilTest {

    @Test
    void testCreateOrder() {
        String orderNo = OrderUtil.createOrderNo();
        log.info("orderNo:{}", orderNo);
    }
}
