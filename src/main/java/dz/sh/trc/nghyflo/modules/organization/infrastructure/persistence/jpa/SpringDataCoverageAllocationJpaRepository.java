package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCoverageAllocationJpaRepository extends JpaRepository<CoverageAllocationJpaEntity, String> {

    List<CoverageAllocationJpaEntity> findByEmployeeIdAndStatus(String employeeId, String status);
}
