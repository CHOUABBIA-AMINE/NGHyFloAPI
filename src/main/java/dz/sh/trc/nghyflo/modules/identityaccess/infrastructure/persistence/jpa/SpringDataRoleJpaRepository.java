/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : SpringDataRoleJpaRepository
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Repository
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa
 *
 * @Description : Spring Data repository for persisted identity roles.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRoleJpaRepository extends JpaRepository<RoleJpaEntity, String> {

    Optional<RoleJpaEntity> findByRoleCode(String roleCode);
}
