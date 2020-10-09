package com.gh.shipmentmanagment.config;

import com.gh.shipmentmanagment.exception.ErrorCodeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger config
 * @Author: Tiger
 * @Date: 2020/10/10
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> responseMessageList = getResponseMessageList();
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gh.shipmentmanagment.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private List<ResponseMessage> getResponseMessageList() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        ErrorCodeEnum[] errorCodeEnums = ErrorCodeEnum.values();
        for (ErrorCodeEnum errorEnums : errorCodeEnums) {
            ResponseMessageBuilder responseMessageBuilder = new ResponseMessageBuilder();
            responseMessageBuilder.code(errorEnums.getCode());
            responseMessageBuilder.message(errorEnums.getMsg());
            responseMessageBuilder.responseModel(new ModelRef(errorEnums.getMsg()));
            ResponseMessage responseMessage = responseMessageBuilder.build();
            responseMessageList.add(responseMessage);
        }
        return responseMessageList;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("shipment API")
                .description("shipment")
                .contact(new Contact("tiger", "", "tiger886@126.com"))
                .version("1.0")
                .build();
    }
}
