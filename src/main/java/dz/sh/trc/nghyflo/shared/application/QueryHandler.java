/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : QueryHandler
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Interface
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.application
 *
 * @Description : Query handler contract.
 *
 */
package dz.sh.trc.nghyflo.shared.application;
public interface QueryHandler<Q extends Query<R>,R> {R handle(Q query);}
