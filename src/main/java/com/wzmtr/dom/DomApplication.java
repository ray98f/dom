package com.wzmtr.dom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.wzmtr.dom.**.mapper")
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableScheduling
@EnableAsync
@EnableConfigurationProperties
public class DomApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.wzmtr.dom.DomApplication.class, args);
    }

}
