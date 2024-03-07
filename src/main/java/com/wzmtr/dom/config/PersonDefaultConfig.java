package com.wzmtr.dom.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 人员同步默认配置
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Data
@Component
@ConfigurationProperties(prefix = "user.default")
public class PersonDefaultConfig {

    private String password;
    private String userType;
    private String loginFlag;
    private String createBy;
    private String updateBy;
}
