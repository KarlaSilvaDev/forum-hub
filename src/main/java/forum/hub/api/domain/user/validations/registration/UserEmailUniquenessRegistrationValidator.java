package forum.hub.api.domain.user.validations.registration;

import forum.hub.api.domain.DataValidationException;
import forum.hub.api.domain.user.UserRepository;
import forum.hub.api.domain.user.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEmailUniquenessRegistrationValidator implements UserRegistrationValidator{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UserRegistrationDTO data) {
        var emailIsRegistered = userRepository.existsByEmail(data.email());

        if (emailIsRegistered){
            throw new DataValidationException("Email já cadastrado.");
        }
    }
}
