package forum.hub.api.domain.topic.validations.update;

import forum.hub.api.domain.DataValidationException;
import forum.hub.api.domain.topic.TopicRepository;
import forum.hub.api.domain.topic.dto.TopicUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicUniquenessUpdateValidator implements TopicUpdateValidator{

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validate(TopicUpdateDTO data, Long id) {
        var topicIsRegistered = topicRepository.existsByTitleAndMessageExcludingId(data.title(), data.message(), id);

        if(topicIsRegistered){
            throw new DataValidationException("Não é permitido o cadastro de tópicos duplicados.");
        }
    }
}
