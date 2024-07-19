package forum.hub.api.domain.course.validations.registration;

import forum.hub.api.domain.DataValidationException;
import forum.hub.api.domain.course.Category;
import forum.hub.api.domain.course.dto.CourseRegistrationDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CourseCategoryExistenceRegistrationValidator implements CourseRegistrationValidator{

    @Override
    public void validate(CourseRegistrationDTO data) {
        var categories = Arrays.stream(Category.values()).map(c -> c.toString()).toList();
        var categoryExists = categories.contains(data.category());

        if (!categoryExists){
            var message = "A categoria informada não é válida. Informe uma dessas categorias: " + categories.toString();
            throw new DataValidationException(message);
        }
    }
}
