package com.gas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gas.aop.LoginAOP;
import com.gas.aop.WXAOP;
import com.gas.config.ApplicationStartup;

@SpringBootApplication
public class GasFillingStationApplication extends WebMvcConfigurerAdapter
        implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(GasFillingStationApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(80);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WXAOP()).addPathPatterns("/wx/**");
        registry.addInterceptor(new LoginAOP()).addPathPatterns("/api/**");
    }
}
