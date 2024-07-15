package forum.hub.api.domain.user;

import forum.hub.api.domain.profile.ProfileRepository;
import forum.hub.api.domain.user.dto.UserDetailsDTO;
import forum.hub.api.domain.user.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    private String defaultProfileName = "membro";

    public UserDetailsDTO register(UserRegistrationDTO data) {
        var profile = profileRepository.findDefaultProfile(defaultProfileName);
        var user = new User(data, profile);

        userRepository.save(user);

        return new UserDetailsDTO(user);
    }
}
