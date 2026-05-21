/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : OptimisticConcurrencyException
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Class
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.exception
 *
 * @Description : Specific domain exception: OptimisticConcurrencyException.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.exception;

public class OptimisticConcurrencyException extends DomainException {public OptimisticConcurrencyException(String message){super(message);} }
