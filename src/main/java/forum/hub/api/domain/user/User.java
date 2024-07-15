package forum.hub.api.domain.user;

import forum.hub.api.domain.profile.Profile;
import forum.hub.api.domain.user.dto.UserRegistrationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_profiles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "profile_id", nullable = false)
    )
    private Set<Profile> profiles;

    public User(UserRegistrationDTO data, Profile defaultProfile) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.profiles = new HashSet<>();
        profiles.add(defaultProfile);
    }
}
