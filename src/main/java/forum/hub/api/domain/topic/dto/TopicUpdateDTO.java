package forum.hub.api.domain.topic.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record TopicUpdateDTO(
        @JsonAlias({"titulo"})
        String title,
        @JsonAlias({"mensagem"})
        String message,
        @JsonAlias({"autorId"})
        Long authorId,
        @JsonAlias({"cursoId"})
        Long courseId) {
}
