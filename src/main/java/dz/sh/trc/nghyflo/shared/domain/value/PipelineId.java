/**
 *
 * @Project     : NGHyFloAPI
 * @Product     : NGHyFlo — New Generation Hydrocarbon Flow Intelligence Platform
 * @Author      : NGHyFlo Engineering Team
 * @Owner       : Sonatrach / TRC Digitalization Initiative
 *
 * @Name        : PipelineId
 * @CreatedOn   : 2026-05-21
 * @UpdatedOn   : 2026-05-22
 *
 * @Type        : Record
 * @Layer       : Shared Kernel
 * @Package     : dz.sh.trc.nghyflo.shared.domain.value
 *
 * @Description : Strongly typed UUID-backed identifier for a hydrocarbon pipeline.
 *
 */
package dz.sh.trc.nghyflo.shared.domain.value;

import java.util.UUID;

public record PipelineId(String value) {

    public PipelineId {
        value = requireValidUuid(value, "PipelineId");
    }

    public static PipelineId newId() {
        return new PipelineId(UUID.randomUUID().toString());
    }

    public static PipelineId of(String value) {
        return new PipelineId(value);
    }

    private static String requireValidUuid(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        UUID.fromString(value);
        return value;
    }
}
