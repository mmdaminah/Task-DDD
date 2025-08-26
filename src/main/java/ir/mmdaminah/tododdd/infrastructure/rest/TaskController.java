package ir.mmdaminah.tododdd.infrastructure.rest;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.usecase.task.command.*;
import ir.mmdaminah.tododdd.usecase.task.query.GetTaskByIdQuery;
import ir.mmdaminah.tododdd.usecase.task.query.ListTasksQuery;
import ir.mmdaminah.tododdd.usecase.task.query.TaskQueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskCommandHandler taskCommandHandler;
    private final TaskQueryHandler taskQueryHandler;


    public TaskController(TaskCommandHandler taskCommandHandler, TaskQueryHandler taskQueryHandler) {
        this.taskCommandHandler = taskCommandHandler;
        this.taskQueryHandler = taskQueryHandler;
    }

    @PostMapping
    public Task create(@RequestBody TaskRequest req) {
        return taskCommandHandler.handle(new CreateTaskCommand(req.getTitle(), req.getDescription()));
    }

    @PutMapping("/{id}")
    public Optional<Task> update(@PathVariable UUID id, @RequestBody TaskRequest req) {
        return taskCommandHandler.handle(new UpdateTaskCommand(id, req.getTitle(), req.getDescription()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        taskCommandHandler.handle(new DeleteTaskCommand(id));
    }

    @GetMapping("/{id}")
    public Optional<Task> getById(@PathVariable UUID id) {
        return taskQueryHandler.handle(new GetTaskByIdQuery(id));
    }

    @GetMapping
    public List<Task> list() {
        return taskQueryHandler.handle(new ListTasksQuery());
    }

    @PatchMapping("/{id}/complete")
    public boolean complete(@PathVariable UUID id) {
        return taskCommandHandler.handle(new CompleteTaskCommand(id));
    }

}
