package forum.hub.api.domain.course.validations.update;

import forum.hub.api.domain.DataValidationException;
import forum.hub.api.domain.course.Category;
import forum.hub.api.domain.course.CourseRepository;
import forum.hub.api.domain.course.dto.CourseUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseUniquenessUpdateValidator implements CourseUpdateValidator{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void validate(CourseUpdateDTO data, Long id) {
        String name = null;
        Category category = null;

        if (data.name() == null && data.category() == null){
            throw new DataValidationException("Informe algum dado para atualização.");
        }else if (data.name() != null && data.category() != null){
            name = data.name();
            category = Category.valueOf(data.category());
        }else if (data.name() != null){
            name = data.name();
            category = courseRepository.getReferenceById(id).getCategory();
        }else{
            name = courseRepository.getReferenceById(id).getName();
            category = Category.valueOf(data.category());
        }
        var courseIsRegistered = courseRepository.existsByNameAndCategoryExcludingId(name, category, id);

        if(courseIsRegistered){
            throw new DataValidationException("Este curso já está registrado na plataforma.");
        }
    }
}
