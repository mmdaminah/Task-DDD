package ir.mmdaminah.tododdd.usecase.task.command;

import java.util.UUID;

import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteTaskHandler {
    private final TaskRepository repository;

    public DeleteTaskHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handle(DeleteTaskCommand cmd) {
        repository.delete(new TaskId(cmd.getId()));
    }
}
