/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : NoCrossModuleJpaAccessTest
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Architecture Test
 * @Package     : dz.sh.trc.nghyflo.architecture
 *
 * @Description : Prevents modules from bypassing bounded contexts through direct JPA repository access.
 *
 */
package dz.sh.trc.nghyflo.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class NoCrossModuleJpaAccessTest {
    private static final String BASE_PACKAGE = "dz.sh.trc.nghyflo";
    private static final JavaClasses CLASSES = new ClassFileImporter().importPackages(BASE_PACKAGE);

    @Test
    void applicationLayerMustNotDependOnSpringDataRepositoryTypes() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "org.springframework.data.repository..",
                        "org.springframework.data.jpa.repository.."
                );

        rule.check(CLASSES);
    }

    @Test
    void domainLayerMustNotDependOnSpringDataRepositoryTypes() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "org.springframework.data.repository..",
                        "org.springframework.data.jpa.repository.."
                );

        rule.check(CLASSES);
    }

    @Test
    void apiLayerMustNotDependOnJpaInfrastructurePackages() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..api..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure.persistence.jpa..");

        rule.check(CLASSES);
    }
}
