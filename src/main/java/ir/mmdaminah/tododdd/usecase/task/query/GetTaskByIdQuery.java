package ir.mmdaminah.tododdd.usecase.task.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GetTaskByIdQuery {
    private UUID id;
}
