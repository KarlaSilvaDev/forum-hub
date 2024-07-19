package forum.hub.api.domain.user;

import forum.hub.api.domain.DataValidationException;
import forum.hub.api.domain.profile.ProfileRepository;
import forum.hub.api.domain.user.dto.UserDetailsDTO;
import forum.hub.api.domain.user.dto.UserRegistrationDTO;
import forum.hub.api.domain.user.dto.UserUpdateDTO;
import forum.hub.api.domain.user.validations.registration.UserRegistrationValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String DEFAULT_PROFILE_NAME = "membro";

    @Autowired
    private List<UserRegistrationValidator> userRegistrationValidators;

    public UserDetailsDTO register(UserRegistrationDTO data) {
        userRegistrationValidators.forEach(v -> v.validate(data));

        var profile = profileRepository.findDefaultProfile(DEFAULT_PROFILE_NAME);
        var hashedPassword = passwordEncoder.encode(data.password());
        var user = new User(data, profile);
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return new UserDetailsDTO(user);
    }

    public Page<UserDetailsDTO> list(Pageable pagination) {
        var page = userRepository.findAll(pagination).map(UserDetailsDTO::new);
        return page;
    }

    public UserDetailsDTO detail(Long id) {
        var userExists = userRepository.existsById(id);

        if (!userExists){
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        var user = userRepository.getReferenceById(id);
        return new UserDetailsDTO(user);
    }

    public UserDetailsDTO update(UserUpdateDTO data, Long id) {
        var user = userRepository.getReferenceById(id);

        if (data.password() != null){
            var hashedPassword = passwordEncoder.encode(data.password());
            var dataWithHashedPassword = new UserUpdateDTO(data.name(), hashedPassword);
            user.update(dataWithHashedPassword);
        }else{
            user.update(data);
        }

        return new UserDetailsDTO(user);
    }

    public void delete(Long id) {
        var userExists = userRepository.existsById(id);

        if (!userExists){
            throw new DataValidationException("Usuário não encontrado");
        }

        userRepository.deleteById(id);
    }
}
