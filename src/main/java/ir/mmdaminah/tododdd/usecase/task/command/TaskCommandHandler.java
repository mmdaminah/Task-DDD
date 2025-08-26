package ir.mmdaminah.tododdd.usecase.task.command;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskCommandHandler {

    private final TaskService taskService;

    TaskCommandHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    public Task handle(CreateTaskCommand cmd) {
        Task task = Task.createTask(TaskId.newId(), cmd.getTitle(), cmd.getDescription(), TaskStatus.PENDING);

        taskService.persist(task);

        return task;
    }

    public void handle(DeleteTaskCommand cmd) {
        taskService.delete(new TaskId(cmd.getId()));
    }

    public Optional<Task> handle(UpdateTaskCommand cmd) {
        var task = taskService.loadTask(new TaskId(cmd.getId()));

        return taskService.update(task, cmd);
    }

    public boolean handle(CompleteTaskCommand cmd) {
        var task = taskService.loadTask(new TaskId(cmd.getId()));

        return taskService.markCompleteTask(task);
    }

}
