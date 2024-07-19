package forum.hub.api.domain.course.validations.registration;

import forum.hub.api.domain.DataValidationException;
import forum.hub.api.domain.course.Category;
import forum.hub.api.domain.course.CourseRepository;
import forum.hub.api.domain.course.dto.CourseRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseUniquenessRegistrationValidator implements CourseRegistrationValidator {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void validate(CourseRegistrationDTO data) {
        var courseIsRegistered = courseRepository.existsByNameAndCategory(data.name(), Category.valueOf(data.category()));

        if(courseIsRegistered){
            throw new DataValidationException("Este curso já está registrado na plataforma.");
        }
    }
}
