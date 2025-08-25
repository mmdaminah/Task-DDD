package ir.mmdaminah.tododdd.usecase.task.query;

import java.util.UUID;

public class GetTaskByIdQuery {
    private UUID id;
    public GetTaskByIdQuery(UUID id) { this.id = id; }
    public UUID getId() { return id; }
}
