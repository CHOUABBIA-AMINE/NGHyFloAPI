/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationConfiguration
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Configuration
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.organization.infrastructure.configuration
 *
 * @Description : Wires organization application services and persistence adapters.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.infrastructure.configuration;

import dz.sh.trc.nghyflo.modules.organization.application.port.CoverageAllocationPort;
import dz.sh.trc.nghyflo.modules.organization.application.port.EmployeeLookupPort;
import dz.sh.trc.nghyflo.modules.organization.application.service.OrganizationCoverageService;
import dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa.JpaCoverageAllocationAdapter;
import dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa.JpaEmployeeLookupAdapter;
import dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa.OrganizationJpaMapper;
import dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa.SpringDataCoverageAllocationJpaRepository;
import dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa.SpringDataEmployeeJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrganizationConfiguration {

    @Bean
    public OrganizationJpaMapper organizationJpaMapper() {
        return new OrganizationJpaMapper();
    }

    @Bean
    public EmployeeLookupPort employeeLookupPort(
            SpringDataEmployeeJpaRepository repository,
            OrganizationJpaMapper mapper
    ) {
        return new JpaEmployeeLookupAdapter(repository, mapper);
    }

    @Bean
    public CoverageAllocationPort coverageAllocationPort(
            SpringDataCoverageAllocationJpaRepository repository,
            OrganizationJpaMapper mapper
    ) {
        return new JpaCoverageAllocationAdapter(repository, mapper);
    }

    @Bean
    public OrganizationCoverageService organizationCoverageService(
            EmployeeLookupPort employeeLookupPort,
            CoverageAllocationPort coverageAllocationPort
    ) {
        return new OrganizationCoverageService(employeeLookupPort, coverageAllocationPort);
    }
}
