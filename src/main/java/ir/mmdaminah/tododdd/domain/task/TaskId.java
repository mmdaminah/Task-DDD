package ir.mmdaminah.tododdd.domain.task;

import java.util.UUID;

public class TaskId {
    private final UUID value;

    public TaskId(UUID value) { this.value = value; }
    public UUID getValue() { return value; }

    public static TaskId newId() { return new TaskId(UUID.randomUUID()); }
}
