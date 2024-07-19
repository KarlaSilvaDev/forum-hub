package forum.hub.api.domain.course.validations.update;

import forum.hub.api.domain.course.dto.CourseUpdateDTO;

public interface CourseUpdateValidator {
    void validate(CourseUpdateDTO data, Long id);
}
