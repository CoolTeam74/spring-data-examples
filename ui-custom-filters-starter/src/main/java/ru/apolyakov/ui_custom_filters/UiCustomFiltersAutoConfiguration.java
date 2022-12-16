package ru.apolyakov.ui_custom_filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@ComponentScan
@Slf4j
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class UiCustomFiltersAutoConfiguration {
}
