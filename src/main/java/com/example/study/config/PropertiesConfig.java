package com.example.study.config;

import com.example.study.support.Constants;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = Constants.BASE_PACKAGE)
public class PropertiesConfig {
}
