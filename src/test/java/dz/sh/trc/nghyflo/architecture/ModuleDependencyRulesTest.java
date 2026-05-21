/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : ModuleDependencyRulesTest
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Architecture Test
 * @Package     : dz.sh.trc.nghyflo.architecture
 *
 * @Description : Enforces allowed dependency direction between NGHyFlo module layers.
 *
 */
package dz.sh.trc.nghyflo.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ModuleDependencyRulesTest {
    private static final String BASE_PACKAGE = "dz.sh.trc.nghyflo";
    private static final JavaClasses CLASSES = new ClassFileImporter().importPackages(BASE_PACKAGE);

    @Test
    void apiLayerMustNotDependOnInfrastructureLayer() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..api..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..");

        rule.check(CLASSES);
    }

    @Test
    void applicationLayerMustNotDependOnApiLayer() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAPackage("..api..");

        rule.check(CLASSES);
    }

    @Test
    void applicationLayerMustNotDependOnInfrastructureLayer() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..");

        rule.check(CLASSES);
    }
}
