/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : RoleJpaEntity
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Entity
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa
 *
 * @Description : JPA projection of an NGHyFlo identity role.
 *
 */
package dz.sh.trc.nghyflo.modules.identityaccess.infrastructure.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ng_role", schema = "nghyflo")
public class RoleJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "role_code", nullable = false, unique = true, length = 128)
    private String roleCode;

    protected RoleJpaEntity() {
    }

    public RoleJpaEntity(String id, String roleCode) {
        this.id = id;
        this.roleCode = roleCode;
    }

    public String id() {
        return id;
    }

    public String roleCode() {
        return roleCode;
    }
}
