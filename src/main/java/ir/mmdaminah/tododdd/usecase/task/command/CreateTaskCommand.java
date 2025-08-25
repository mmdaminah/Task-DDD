package ir.mmdaminah.tododdd.usecase.task.command;

public class CreateTaskCommand {
    private String title;
    private String description;

    public CreateTaskCommand(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
