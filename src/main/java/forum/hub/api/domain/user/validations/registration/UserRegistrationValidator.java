package forum.hub.api.domain.user.validations.registration;

import forum.hub.api.domain.user.dto.UserRegistrationDTO;

public interface UserRegistrationValidator {
    void validate(UserRegistrationDTO data);
}
