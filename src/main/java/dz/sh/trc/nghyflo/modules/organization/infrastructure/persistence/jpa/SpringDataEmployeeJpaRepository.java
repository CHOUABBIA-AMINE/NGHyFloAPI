package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEmployeeJpaRepository extends JpaRepository<EmployeeJpaEntity, String> {
}
