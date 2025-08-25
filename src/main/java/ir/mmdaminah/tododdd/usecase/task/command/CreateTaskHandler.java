package ir.mmdaminah.tododdd.usecase.task.command;


import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import ir.mmdaminah.tododdd.domain.task.TaskStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTaskHandler {
    private final TaskRepository repository;

    public CreateTaskHandler(TaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Task handle(CreateTaskCommand cmd) {
        Task task = new Task(TaskId.newId(), cmd.getTitle(), cmd.getDescription(), TaskStatus.PENDING);
        return repository.save(task);
    }
}
