package forum.hub.api.domain.course.validations.registration;

import forum.hub.api.domain.course.dto.CourseRegistrationDTO;

public interface CourseRegistrationValidator {
    void validate(CourseRegistrationDTO data);
}
