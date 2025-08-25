package ir.mmdaminah.tododdd.usecase.task.command;

import java.util.Optional;
import java.util.UUID;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateTaskHandler {
    private final TaskRepository repository;

    public UpdateTaskHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<Task> handle(UpdateTaskCommand cmd) {
        return repository.findById(new TaskId(cmd.getId()))
                .map(task -> {
                    task.update(cmd.getTitle(), cmd.getDescription());
                    return repository.save(task);
                });
    }
}
