package cn.cucsi.bsd.ucc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"cn.cucsi.bsd.ucc"})
@MapperScan("cn.cucsi.bsd.ucc.common.mapper")
@EnableScheduling
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
