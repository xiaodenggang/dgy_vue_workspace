package dgy.hbdgy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("dgy.hbdgy.mapper")
public class HbdgyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbdgyApplication.class, args);
    }

}
