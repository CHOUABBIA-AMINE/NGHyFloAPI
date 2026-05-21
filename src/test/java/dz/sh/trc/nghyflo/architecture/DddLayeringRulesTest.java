/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : DddLayeringRulesTest
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Architecture Test
 * @Package     : dz.sh.trc.nghyflo.architecture
 *
 * @Description : Enforces DDD layering rules for the NGHyFlo modular monolith.
 *
 */
package dz.sh.trc.nghyflo.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DddLayeringRulesTest {
    private static final String BASE_PACKAGE = "dz.sh.trc.nghyflo";
    private static final JavaClasses CLASSES = new ClassFileImporter().importPackages(BASE_PACKAGE);

    @Test
    void domainLayerMustNotDependOnInfrastructureLayer() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..");

        rule.check(CLASSES);
    }

    @Test
    void domainLayerMustNotDependOnApiLayer() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..api..");

        rule.check(CLASSES);
    }

    @Test
    void domainLayerMustNotDependOnSpringFramework() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "org.springframework..",
                        "jakarta.persistence..",
                        "javax.persistence.."
                );

        rule.check(CLASSES);
    }
}
