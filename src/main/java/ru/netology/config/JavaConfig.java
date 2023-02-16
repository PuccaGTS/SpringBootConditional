package ru.netology.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.systemProfile.DevProfile;
import ru.netology.systemProfile.ProductionProfile;
import ru.netology.systemProfile.SystemProfile;


@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    public SystemProfile devProfile(){
        return new DevProfile();
    }
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false")
    @Bean
    public SystemProfile prodProfile(){
        return new ProductionProfile();
    }
}
