package ir.mmdaminah.tododdd.usecase.task.query;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetTaskByIdHandler {
    private final TaskRepository repository;

    public GetTaskByIdHandler(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> handle(GetTaskByIdQuery query) {
        return repository.findById(new TaskId(query.getId()));
    }
}
