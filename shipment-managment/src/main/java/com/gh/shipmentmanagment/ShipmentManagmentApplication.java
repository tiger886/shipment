package com.gh.shipmentmanagment;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author: Tiger
 * @Date: 2020/10/10
 */

@SpringBootApplication
@EnableSwagger2
@EnableSwaggerBootstrapUI
@ComponentScan(basePackages = {"com.gh.shipmentmanagment.**.controller", "com.gh.shipmentmanagment.**.service", "com.gh.shipmentmanagment.**.config", "com.gh.shipmentmanagment.**.exception"})
@MapperScan(basePackages = {"com.gh.shipmentmanagment.**.model"})
public class ShipmentManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipmentManagmentApplication.class, args);
    }

}
