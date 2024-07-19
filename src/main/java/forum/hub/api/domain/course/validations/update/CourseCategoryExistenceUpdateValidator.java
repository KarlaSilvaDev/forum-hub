package forum.hub.api.domain.course.validations.update;

import forum.hub.api.domain.DataValidationException;
import forum.hub.api.domain.course.Category;
import forum.hub.api.domain.course.dto.CourseRegistrationDTO;
import forum.hub.api.domain.course.dto.CourseUpdateDTO;
import forum.hub.api.domain.course.validations.registration.CourseRegistrationValidator;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CourseCategoryExistenceUpdateValidator implements CourseUpdateValidator {

    @Override
    public void validate(CourseUpdateDTO data, Long id) {
        if (data.category() != null) {
            var categories = Arrays.stream(Category.values()).map(c -> c.toString()).toList();
            var categoryExists = categories.contains(data.category());

            if (!categoryExists) {
                var message = "A categoria informada não é válida. Informe uma dessas categorias: " + String.join(", ", categories);
                throw new DataValidationException(message);
            }
        }
    }
}
