package org.kzjy.sxup_phone_wkl_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.kzjy.sxup_phone_wkl_demo.dao")
public class SxupPhoneWklDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxupPhoneWklDemoApplication.class, args);
    }

}
