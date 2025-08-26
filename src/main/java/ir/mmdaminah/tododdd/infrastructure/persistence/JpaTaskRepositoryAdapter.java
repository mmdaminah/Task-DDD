package ir.mmdaminah.tododdd.infrastructure.persistence;

import java.util.*;

import ir.mmdaminah.tododdd.domain.task.Task;
import ir.mmdaminah.tododdd.domain.task.TaskId;
import ir.mmdaminah.tododdd.domain.task.TaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTaskRepositoryAdapter implements TaskRepository {
    private final SpringDataTaskRepository taskRepository;

    public JpaTaskRepositoryAdapter(SpringDataTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        TaskJpaEntity entity = new TaskJpaEntity(
                task.getId().getValue(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
        taskRepository.save(entity);
        return task;
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return taskRepository.findById(id.getValue())
                .map(e -> new Task(new TaskId(e.getId()), e.getTitle(), e.getDescription(), e.getStatus()));
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll().stream()
                .map(e -> new Task(new TaskId(e.getId()), e.getTitle(), e.getDescription(), e.getStatus()))
                .toList();
    }

    @Override
    public void delete(TaskId id) {
        taskRepository.deleteById(id.getValue());
    }

    @Override
    public Task update(Task task) {
        var tsk = this.findById(task.getId());

        return tsk.map(taskItem -> {
            if (task.getTitle() != null) {
                taskItem.setTitle(task.getTitle());
            }
            if (task.getDescription() != null) {
                taskItem.setDescription(task.getDescription());
            }
            return taskItem;
        }).orElse(null);
    }
}
