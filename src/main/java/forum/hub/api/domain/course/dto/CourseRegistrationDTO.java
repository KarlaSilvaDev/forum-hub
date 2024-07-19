package forum.hub.api.domain.course.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRegistrationDTO (
        @JsonAlias("nome")
        @NotBlank(message = "{name.required}")
        String name,
        @JsonAlias("categoria")
        @NotNull(message = "{category.required}")
        String category
){ }
