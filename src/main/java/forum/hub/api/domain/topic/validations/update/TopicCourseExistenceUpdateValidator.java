package forum.hub.api.domain.topic.validations.update;

import forum.hub.api.domain.course.CourseRepository;
import forum.hub.api.domain.topic.dto.TopicRegistrationDTO;
import forum.hub.api.domain.topic.dto.TopicUpdateDTO;
import forum.hub.api.domain.topic.validations.registration.TopicRegistrationValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicCourseExistenceUpdateValidator implements TopicUpdateValidator {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void validate(TopicUpdateDTO data, Long id) {
        if (data.courseId() != null){
            var courseExists = courseRepository.existsById(data.courseId());

            if(!courseExists){
                throw new EntityNotFoundException("Curso não encontrado. Informe um id válido.");
            }
        }
    }
}
