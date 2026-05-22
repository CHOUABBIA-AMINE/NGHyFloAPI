package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import dz.sh.trc.nghyflo.modules.organization.application.port.CoverageAllocationPort;
import dz.sh.trc.nghyflo.modules.organization.domain.model.AssignmentStatus;
import dz.sh.trc.nghyflo.modules.organization.domain.model.CoverageAllocation;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class JpaCoverageAllocationAdapter implements CoverageAllocationPort {
    private final SpringDataCoverageAllocationJpaRepository repository;
    private final OrganizationJpaMapper mapper;

    public JpaCoverageAllocationAdapter(SpringDataCoverageAllocationJpaRepository repository, OrganizationJpaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoverageAllocation> findActiveByEmployeeId(EmployeeId employeeId) {
        return repository.findByEmployeeIdAndStatus(employeeId.value(), AssignmentStatus.ACTIVE.name())
                .stream()
                .map(mapper::toAllocation)
                .toList();
    }
}
