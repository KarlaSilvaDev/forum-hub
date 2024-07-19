package forum.hub.api.domain.topic.validations.update;

import forum.hub.api.domain.topic.dto.TopicUpdateDTO;
import forum.hub.api.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicAuthorExistenceUpdateValidator implements TopicUpdateValidator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(TopicUpdateDTO data, Long id) {
        if (data.authorId() != null){
            var userExists = userRepository.existsById(data.authorId());

            if(!userExists){
                throw new EntityNotFoundException("Autor não encontrado. Informe um id válido.");
            }
        }
    }
}
