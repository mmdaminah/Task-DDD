package ir.mmdaminah.tododdd.infrastructure.persistence;

import java.util.*;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTaskRepositoryAdapter implements TaskRepository {
    private final SpringDataTaskRepository springRepo;

    public JpaTaskRepositoryAdapter(SpringDataTaskRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Task save(Task task) {
        TaskJpaEntity entity = new TaskJpaEntity(
            task.getId().getValue(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus()
        );
        springRepo.save(entity);
        return task;
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return springRepo.findById(id.getValue())
                .map(e -> new Task(new TaskId(e.getId()), e.getTitle(), e.getDescription(), e.getStatus()));
    }

    @Override
    public List<Task> findAll() {
        return springRepo.findAll().stream()
                .map(e -> new Task(new TaskId(e.getId()), e.getTitle(), e.getDescription(), e.getStatus()))
                .toList();
    }

    @Override
    public void delete(TaskId id) {
        springRepo.deleteById(id.getValue());
    }
}
