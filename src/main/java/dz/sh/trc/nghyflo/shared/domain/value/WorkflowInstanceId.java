/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo - New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : WorkflowInstanceId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for a workflow instance.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

public record WorkflowInstanceId(String value) {

    public WorkflowInstanceId {
        value = IdentifierValues.requireUuid(value, "WorkflowInstanceId");
    }

    public static WorkflowInstanceId newId() {
        return new WorkflowInstanceId(IdentifierValues.newUuid());
    }

    public static WorkflowInstanceId of(String value) {
        return new WorkflowInstanceId(value);
    }
}
