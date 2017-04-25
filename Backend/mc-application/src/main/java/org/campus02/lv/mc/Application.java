package org.campus02.lv.mc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "org.campus02" })
@EnableJpaRepositories(basePackages = { "org.campus02" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}