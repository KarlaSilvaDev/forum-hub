package forum.hub.api.domain.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByNameAndCategory(String name, Category category);

    @Query("SELECT COUNT(c) > 0 FROM Course c WHERE c.name = :name AND c.category = :category AND c.id <> :id")
    boolean existsByNameAndCategoryExcludingId(String name, Category category, Long id);
}
