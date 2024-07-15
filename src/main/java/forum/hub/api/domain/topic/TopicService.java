package forum.hub.api.domain.topic;

import forum.hub.api.domain.course.CourseRepository;
import forum.hub.api.domain.topic.dto.TopicDetailsDTO;
import forum.hub.api.domain.topic.dto.TopicRegistrationDTO;
import forum.hub.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    public TopicDetailsDTO register(TopicRegistrationDTO data) {
        var author = userRepository.getReferenceById(data.authorId());
        var course = courseRepository.getReferenceById(data.courseId());
        var topic = new Topic(null, data.title(), data.message(), LocalDateTime.now(), Status.ABERTO, author, course);

        topicRepository.save(topic);

        return new TopicDetailsDTO(topic);
    }

    public Page<TopicDetailsDTO> list(Pageable pagination) {
        var page = topicRepository.findAll(pagination).map(TopicDetailsDTO::new);
        return page;
    }

    public TopicDetailsDTO detail(Long id) {
        var topicDetails = topicRepository.getReferenceById(id);
        return new TopicDetailsDTO(topicDetails);
    }

    public TopicDetailsDTO update(TopicRegistrationDTO data, Long id) {
        var topic = topicRepository.getReferenceById(id);
        var user = userRepository.getReferenceById(data.authorId());
        var course = courseRepository.getReferenceById(data.courseId());
        topic.updateData(data, user, course);

        return new TopicDetailsDTO(topic);
    }

    public void delete(Long id) {
        topicRepository.deleteById(id);
    }
}