/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AdvisoryModuleIsolationTest
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Architecture Test
 * @Package     : dz.sh.trc.nghyflo.architecture
 *
 * @Description : Ensures advisory domains cannot mutate operational truth through workflow or publication commands.
 *
 */
package dz.sh.trc.nghyflo.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class AdvisoryModuleIsolationTest {
    private static final String BASE_PACKAGE = "dz.sh.trc.nghyflo";
    private static final JavaClasses CLASSES = new ClassFileImporter().importPackages(BASE_PACKAGE);

    @Test
    void intelligenceModuleMustNotDependOnWorkflowApprovalCommands() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..modules.intelligence..")
                .should().dependOnClassesThat().haveSimpleNameContaining("Approve");

        rule.check(CLASSES);
    }

    @Test
    void digitalTwinModuleMustNotDependOnOperationalPublicationCommands() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..modules.digitaltwin..")
                .should().dependOnClassesThat().haveSimpleNameContaining("Publish");

        rule.check(CLASSES);
    }

    @Test
    void hydraulicsModuleMustNotDependOnIncidentClosureCommands() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..modules.hydraulics..")
                .should().dependOnClassesThat().haveSimpleNameContaining("CloseIncident");

        rule.check(CLASSES);
    }
}
