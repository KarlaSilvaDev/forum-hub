package forum.hub.api.domain.topic;

import forum.hub.api.domain.course.Course;
import forum.hub.api.domain.topic.dto.TopicUpdateDTO;
import forum.hub.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public void update(TopicUpdateDTO data, User user, Course course) {
        if (data.title() != null){
            this.title = data.title();
        }

        if (data.message() != null){
            this.message = data.message();
        }

        if (data.authorId() != null){
            this.author = user;
        }

        if (data.courseId() != null){
            this.course = course;
        }
    }
}