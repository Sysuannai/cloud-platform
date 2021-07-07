package com.yzchn.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author 洛无异
 * @version 1.0.0
 * @ClassName ClpudPlatFromApplication.java
 * @Description TODO
 * @createTime 2021年06月21日 10:01:00
 */
@SpringBootApplication
@ServletComponentScan
public class CloudPlatFromApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudPlatFromApplication.class, args);
    }
}
