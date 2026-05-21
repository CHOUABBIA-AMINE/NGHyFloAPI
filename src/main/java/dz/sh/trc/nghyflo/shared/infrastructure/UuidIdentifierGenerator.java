/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : UuidIdentifierGenerator
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Infrastructure
 * @Package     : dz.sh.trc.nghyflo.shared.infrastructure
 *
 * @Description : Shared infrastructure artifact UuidIdentifierGenerator.
 *
 */
package dz.sh.trc.nghyflo.shared.infrastructure;

import java.util.UUID;
public class UuidIdentifierGenerator implements IdentifierGenerator { public String nextId(){ return UUID.randomUUID().toString(); } }
