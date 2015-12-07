package com.gas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class GasFillingStationApplication extends SpringBootServletInitializer
        implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(GasFillingStationApplication.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(80);
    }
}
