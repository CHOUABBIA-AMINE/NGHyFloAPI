/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OrganizationUnit
 * @CreatedOn   : 2026-05-22
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Class
 * @Layer       : Domain
 * @Package     : dz.sh.trc.nghyflo.modules.organization.domain.model
 *
 * @Description : Organization unit responsible for operational staffing and structure ownership.
 *
 */
package dz.sh.trc.nghyflo.modules.organization.domain.model;

import dz.sh.trc.nghyflo.modules.organization.domain.value.OrganizationUnitCode;
import dz.sh.trc.nghyflo.modules.organization.domain.value.OrganizationUnitId;
import dz.sh.trc.nghyflo.modules.organization.domain.value.StructureId;
import java.util.Optional;

public class OrganizationUnit {
    private final OrganizationUnitId id;
    private final OrganizationUnitCode code;
    private final String name;
    private OrganizationUnitId parentId;
    private StructureId structureId;
    private boolean active;

    public OrganizationUnit(OrganizationUnitId id, OrganizationUnitCode code, String name) {
        if (id == null) {
            throw new IllegalArgumentException("organization unit id is required");
        }
        if (code == null) {
            throw new IllegalArgumentException("organization unit code is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("organization unit name is required");
        }
        this.id = id;
        this.code = code;
        this.name = name.trim();
        this.active = true;
    }

    public void attachToParent(OrganizationUnitId parentId) {
        if (parentId == null) {
            throw new IllegalArgumentException("parent organization unit id is required");
        }
        if (parentId.equals(id)) {
            throw new IllegalArgumentException("organization unit cannot be its own parent");
        }
        this.parentId = parentId;
    }

    public void assignStructure(StructureId structureId) {
        if (structureId == null) {
            throw new IllegalArgumentException("structure id is required");
        }
        this.structureId = structureId;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public OrganizationUnitId id() {
        return id;
    }

    public OrganizationUnitCode code() {
        return code;
    }

    public String name() {
        return name;
    }

    public Optional<OrganizationUnitId> parentId() {
        return Optional.ofNullable(parentId);
    }

    public Optional<StructureId> structureId() {
        return Optional.ofNullable(structureId);
    }

    public boolean active() {
        return active;
    }
}
