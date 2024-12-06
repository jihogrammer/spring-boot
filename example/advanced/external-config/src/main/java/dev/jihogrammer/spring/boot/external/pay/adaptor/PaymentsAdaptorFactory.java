package dev.jihogrammer.spring.boot.external.pay.adaptor;

import dev.jihogrammer.spring.boot.external.pay.application.port.out.Payments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PaymentsAdaptorFactory {

    @Bean
    @Profile("default")
    public Payments mockPayments() {
        return new MockPaymentsAdaptor();
    }

    @Bean
    @Profile("prd")
    public Payments realPayments() {
        return new RealPaymentsAdaptor();
    }

}
