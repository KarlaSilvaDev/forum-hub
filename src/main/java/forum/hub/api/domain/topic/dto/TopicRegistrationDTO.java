package forum.hub.api.domain.topic.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegistrationDTO(
        @NotBlank
        @JsonAlias({"titulo"})
        String title,
        @NotBlank
        @JsonAlias({"mensagem"})
        String message,
        @NotNull
        @JsonAlias({"autorId"})
        Long authorId,
        @NotNull
        @JsonAlias({"cursoId"})
        Long courseId
) {
}
