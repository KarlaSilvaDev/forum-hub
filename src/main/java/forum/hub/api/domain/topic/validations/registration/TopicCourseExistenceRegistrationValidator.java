package forum.hub.api.domain.topic.validations.registration;

import forum.hub.api.domain.course.CourseRepository;
import forum.hub.api.domain.topic.dto.TopicRegistrationDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicCourseExistenceRegistrationValidator implements TopicRegistrationValidator {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void validate(TopicRegistrationDTO data) {
        var courseExists = courseRepository.existsById(data.courseId());

        if(!courseExists){
            throw new EntityNotFoundException("Curso não encontrado. Informe um id válido.");
        }
    }
}
