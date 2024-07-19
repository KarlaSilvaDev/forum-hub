package forum.hub.api.domain.topic.validations.update;

import forum.hub.api.domain.topic.dto.TopicRegistrationDTO;
import forum.hub.api.domain.topic.dto.TopicUpdateDTO;

public interface TopicUpdateValidator {
    void validate(TopicUpdateDTO data, Long id);
}
