package ir.mmdaminah.tododdd.domain.task;

public class Task {
    private TaskId id;
    private String title;
    private String description;
    private TaskStatus status;

    public Task(TaskId id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public static Task createTask(TaskId id, String title, String description, TaskStatus status) {
        return new Task(id, title, description, status);
    }

    public TaskId getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }

    public void setId(TaskId id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void markCompleted() {
        this.status = TaskStatus.COMPLETED;
    }
}
