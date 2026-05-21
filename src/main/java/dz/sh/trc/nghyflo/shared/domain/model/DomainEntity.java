/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : DomainEntity
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.model
 *
 * @Description : Base domain entity identity container.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.model;

public record DomainEntity<I>(I id) {}
