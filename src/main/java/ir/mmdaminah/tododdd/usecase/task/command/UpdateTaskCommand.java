package ir.mmdaminah.tododdd.usecase.task.command;

import java.util.UUID;

public class UpdateTaskCommand {
    private UUID id;
    private String title;
    private String description;

    public UpdateTaskCommand(UUID id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
