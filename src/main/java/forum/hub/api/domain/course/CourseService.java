package forum.hub.api.domain.course;

import forum.hub.api.domain.DataValidationException;
import forum.hub.api.domain.course.dto.CourseDetailsDTO;
import forum.hub.api.domain.course.dto.CourseRegistrationDTO;
import forum.hub.api.domain.course.dto.CourseUpdateDTO;
import forum.hub.api.domain.course.validations.registration.CourseRegistrationValidator;
import forum.hub.api.domain.course.validations.update.CourseUpdateValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private List<CourseRegistrationValidator> courseRegistrationValidators;

    @Autowired
    private List<CourseUpdateValidator> courseUpdateValidators;

    public CourseDetailsDTO register(CourseRegistrationDTO data) {
        courseRegistrationValidators.forEach(v -> v.validate(data));

        var course = new Course(null, data.name(), Category.valueOf(data.category()));
        courseRepository.save(course);

        return new CourseDetailsDTO(course);
    }

    public Page<CourseDetailsDTO> list(Pageable pagination){
        var page = courseRepository.findAll(pagination).map(CourseDetailsDTO::new);
        return page;
    }

    public CourseDetailsDTO detail(Long id){
        var courseExists = courseRepository.existsById(id);

        if (!courseExists){
            throw new EntityNotFoundException("Curso não encontrado.");
        }

        var course = courseRepository.getReferenceById(id);
        return new CourseDetailsDTO(course);
    }

    public CourseDetailsDTO update(CourseUpdateDTO data, Long id) {
        courseUpdateValidators.forEach(v -> v.validate(data, id));
        var course = courseRepository.getReferenceById(id);
        course.update(data);

        return new CourseDetailsDTO(course);
    }

    public void delete(Long id) {
        var courseExists = courseRepository.existsById(id);
        if (!courseExists){
            throw new DataValidationException("Curso não encontrado");
        }
        courseRepository.deleteById(id);
    }
}
