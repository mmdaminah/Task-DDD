package ir.mmdaminah.tododdd.usecase.task.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DeleteTaskCommand {
    private UUID id;
}
