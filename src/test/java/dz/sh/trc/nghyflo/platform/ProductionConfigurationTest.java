package dz.sh.trc.nghyflo.platform;

import java.nio.file.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductionConfigurationTest {
    @Test void productionGuardsPresent() throws Exception {
        String yml = Files.readString(Path.of("src/main/resources/application-production.yml"));
        assertTrue(yml.contains("open-in-view: false"));
        assertFalse(yml.contains("ddl-auto: update"));
        assertFalse(yml.contains("show-sql: true"));
        assertFalse(yml.contains("allowed-origins: \"*\""));
    }
}
