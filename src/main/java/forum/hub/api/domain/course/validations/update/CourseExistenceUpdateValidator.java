package forum.hub.api.domain.course.validations.update;

import forum.hub.api.domain.course.CourseRepository;
import forum.hub.api.domain.course.dto.CourseUpdateDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseExistenceUpdateValidator implements CourseUpdateValidator{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void validate(CourseUpdateDTO data, Long id) {
        var courseExists = courseRepository.existsById(id);

        if(!courseExists){
            throw new EntityNotFoundException("Curso não encontrado. Informe um id válido.");
        }
    }
}
