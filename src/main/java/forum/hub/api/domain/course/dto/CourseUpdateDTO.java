package forum.hub.api.domain.course.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CourseUpdateDTO(
        @JsonAlias("nome")
        String name,
        @JsonAlias("categoria")
        String category
) {
}
