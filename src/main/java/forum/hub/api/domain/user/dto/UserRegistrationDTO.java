package forum.hub.api.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationDTO(@JsonAlias("nome")
                                  @NotBlank
                                  String name,
                                  @NotBlank
                                  @Email
                                  String email,
                                  @JsonAlias("senha")
                                  @NotBlank
                                  String password) {
}
