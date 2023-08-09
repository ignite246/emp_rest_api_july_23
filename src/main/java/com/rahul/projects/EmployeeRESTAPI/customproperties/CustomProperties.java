package com.rahul.projects.EmployeeRESTAPI.customproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@ConfigurationProperties()
@EnableConfigurationProperties
@PropertySource(value="custom-props.properties")
public class CustomProperties {

    Map<String,String> dx = new HashMap<>();
}
