package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import dz.sh.trc.nghyflo.modules.organization.application.port.EmployeeLookupPort;
import dz.sh.trc.nghyflo.modules.organization.domain.model.Employee;
import dz.sh.trc.nghyflo.shared.domain.value.EmployeeId;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

public class JpaEmployeeLookupAdapter implements EmployeeLookupPort {
    private final SpringDataEmployeeJpaRepository repository;
    private final OrganizationJpaMapper mapper;

    public JpaEmployeeLookupAdapter(SpringDataEmployeeJpaRepository repository, OrganizationJpaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(EmployeeId employeeId) {
        return repository.findById(employeeId.value()).map(mapper::toEmployee);
    }
}
