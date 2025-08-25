package ir.mmdaminah.tododdd.infrastructure.rest;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.usecase.task.command.*;
import ir.mmdaminah.tododdd.usecase.task.query.GetTaskByIdHandler;
import ir.mmdaminah.tododdd.usecase.task.query.GetTaskByIdQuery;
import ir.mmdaminah.tododdd.usecase.task.query.ListTasksHandler;
import ir.mmdaminah.tododdd.usecase.task.query.ListTasksQuery;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskHandler createHandler;
    private final UpdateTaskHandler updateHandler;
    private final DeleteTaskHandler deleteHandler;
    private final GetTaskByIdHandler getHandler;
    private final ListTasksHandler listHandler;

    public TaskController(CreateTaskHandler createHandler,
                          UpdateTaskHandler updateHandler,
                          DeleteTaskHandler deleteHandler,
                          GetTaskByIdHandler getHandler,
                          ListTasksHandler listHandler) {
        this.createHandler = createHandler;
        this.updateHandler = updateHandler;
        this.deleteHandler = deleteHandler;
        this.getHandler = getHandler;
        this.listHandler = listHandler;
    }

    @PostMapping
    public Task create(@RequestBody TaskRequest req) {
        return createHandler.handle(new CreateTaskCommand(req.getTitle(), req.getDescription()));
    }

    @PutMapping("/{id}")
    public Optional<Task> update(@PathVariable UUID id, @RequestBody TaskRequest req) {
        return updateHandler.handle(new UpdateTaskCommand(id, req.getTitle(), req.getDescription()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        deleteHandler.handle(new DeleteTaskCommand(id));
    }

    @GetMapping("/{id}")
    public Optional<Task> getById(@PathVariable UUID id) {
        return getHandler.handle(new GetTaskByIdQuery(id));
    }

    @GetMapping
    public List<Task> list() {
        return listHandler.handle(new ListTasksQuery());
    }
}
