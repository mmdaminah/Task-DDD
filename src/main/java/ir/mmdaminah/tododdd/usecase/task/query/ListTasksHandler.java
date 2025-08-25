package ir.mmdaminah.tododdd.usecase.task.query;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTasksHandler {
    private final TaskRepository repository;

    public ListTasksHandler(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> handle(ListTasksQuery query) {
        return repository.findAll();
    }
}
