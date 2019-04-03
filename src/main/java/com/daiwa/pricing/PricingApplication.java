package com.daiwa.pricing;

import com.daiwa.pricing.controller.CommandController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PricingApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PricingApplication.class, args);
        CommandController controller = (CommandController) ctx.getBean("commandController");

        controller.process();
    }
}
