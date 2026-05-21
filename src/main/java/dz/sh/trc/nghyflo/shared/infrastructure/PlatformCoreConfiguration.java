package dz.sh.trc.nghyflo.shared.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlatformCoreConfiguration {

    @Bean
    PlatformClock platformClock() {
        return new SystemPlatformClock();
    }

    @Bean
    IdentifierGenerator identifierGenerator() {
        return new UuidIdentifierGenerator();
    }
}
