package forum.hub.api.domain.course;

import forum.hub.api.domain.course.dto.CourseUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Course")
@Table(name = "courses")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    public void update(CourseUpdateDTO data) {
        if (data.name() != null){
            this.name = data.name();
        }

        if (data.category() != null){
            this.category = Category.valueOf(data.category());
        }
    }
}
