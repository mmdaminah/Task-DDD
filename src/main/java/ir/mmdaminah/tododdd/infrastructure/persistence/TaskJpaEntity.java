package ir.mmdaminah.tododdd.infrastructure.persistence;

import ir.mmdaminah.tododdd.domain.task.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@DynamicUpdate
public class TaskJpaEntity {
    @Id
    private UUID id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
