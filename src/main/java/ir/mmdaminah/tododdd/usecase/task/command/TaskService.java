package ir.mmdaminah.tododdd.usecase.task.command;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void persist(Task task) {
        repository.save(task);
    }

    public void delete(TaskId taskId) {
        repository.delete(taskId);
    }

    public Optional<Task> update(Optional<Task> task, UpdateTaskCommand cmd) {
        return task
                .map(tsk -> {
                    tsk.update(cmd.getTitle(), cmd.getDescription());
                    return repository.update(tsk);
                });
    }

    public Optional<Task> loadTask(TaskId taskId) {
        return repository.findById(taskId);
    }

    public boolean markCompleteTask(Optional<Task> task) {
        return task.map(tsk -> {
                    tsk.markCompleted();
                    repository.save(tsk);
                    return true;
                })
                .orElse(false);
    }

}
