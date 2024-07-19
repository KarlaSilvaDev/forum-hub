package forum.hub.api.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UserUpdateDTO(@JsonAlias("nome")
                            String name,
                            @JsonAlias("senha")
                            String password) {
}
