package ir.mmdaminah.tododdd.infrastructure.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
    private String title;
    private String description;
}
