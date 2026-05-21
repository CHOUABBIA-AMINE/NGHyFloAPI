/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : CommandBus
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-21
 *
 * @Type        : Interface
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.application
 *
 * @Description : Command dispatching contract.
 *
 */
package dz.sh.trc.nghyflo.shared.application;
public interface CommandBus {<C extends Command,R> R dispatch(C command);}
