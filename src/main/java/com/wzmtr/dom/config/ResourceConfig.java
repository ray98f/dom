package com.wzmtr.dom.config;

import com.wzmtr.dom.entity.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Resource配置
 * @author  Ray
 * @version 1.0
 * @date 2024/03/06
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "spring.static")
public class ResourceConfig {
    List<Resource> resources;
}

