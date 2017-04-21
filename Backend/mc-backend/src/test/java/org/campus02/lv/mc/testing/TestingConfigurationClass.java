package org.campus02.lv.mc.testing;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = { "org.campus02" })
@EntityScan(basePackages = { "org.campus02" })
@EnableJpaRepositories(basePackages = { "org.campus02" })
@EnableAutoConfiguration
public class TestingConfigurationClass {

}
