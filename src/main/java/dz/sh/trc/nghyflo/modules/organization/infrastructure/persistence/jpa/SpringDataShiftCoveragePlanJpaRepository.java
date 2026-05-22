package dz.sh.trc.nghyflo.modules.organization.infrastructure.persistence.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataShiftCoveragePlanJpaRepository extends JpaRepository<ShiftCoveragePlanJpaEntity, String> {

    List<ShiftCoveragePlanJpaEntity> findByEmployeeIdAndStatus(String employeeId, String status);
}
