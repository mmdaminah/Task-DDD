package ir.mmdaminah.tododdd.infrastructure.persistence;

import ir.mmdaminah.tododdd.domain.task.TaskStatus;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class TaskJpaEntity {
    @Id
    private UUID id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public TaskJpaEntity() {}

    public TaskJpaEntity(UUID id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
}
