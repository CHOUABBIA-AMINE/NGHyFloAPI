/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : AuditableAggregate
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Interface
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.model
 *
 * @Description : Aggregate exposing audit metadata fields.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.model;

import java.time.Instant;
public interface AuditableAggregate {Instant createdAt(); Instant updatedAt();}
