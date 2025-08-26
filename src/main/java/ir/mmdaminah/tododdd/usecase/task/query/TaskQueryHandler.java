package ir.mmdaminah.tododdd.usecase.task.query;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryHandler {

    private final TaskRepository repository;

    TaskQueryHandler(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> handle(GetTaskByIdQuery query) {
        return repository.findById(new TaskId(query.getId()));
    }

    public List<Task> handle(ListTasksQuery query) {
        return repository.findAll();
    }

}
