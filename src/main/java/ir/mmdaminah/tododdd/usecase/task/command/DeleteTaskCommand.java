package ir.mmdaminah.tododdd.usecase.task.command;

import java.util.UUID;

public class DeleteTaskCommand {
    private UUID id;

    public DeleteTaskCommand(UUID id) { this.id = id; }

    public UUID getId() { return id; }
}
