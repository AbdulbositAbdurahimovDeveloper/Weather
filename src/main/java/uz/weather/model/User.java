package uz.weather.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.weather.enums.Role;
import uz.weather.model.Abs.AbsLongEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Represents a user in the system.
 * <p>
 * This entity implements {@link UserDetails} to integrate with Spring Security for authentication and authorization.
 * It has a one-to-one relationship with {@link UserProfile}, which stores additional user information.
 * Soft deletion is enabled via Hibernate's {@link SQLDelete} and {@link SQLRestriction} annotations.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "profile")
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted=false")
public class User extends AbsLongEntity implements UserDetails {

    /**
     * The unique username for authentication. Cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The unique email address of the user. Used for communication and potentially for login. Cannot be null.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The hashed password for the user. Cannot be null.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role of the user, which determines their permissions in the system.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * A flag to indicate if the user's account is locked. A locked account cannot be authenticated.
     */
    private boolean accountNonLocked = true;

    /**
     * A flag to indicate if the user's account is enabled. A disabled account cannot be authenticated.
     * Typically set to true after email verification.
     */
    private boolean enabled = false;

    /**
     * A flag for soft deletion. If true, the user is considered deleted and will not be returned by queries.
     */
    private boolean deleted = false;

    /**
     * A flag to indicate if the user has verified their email address.
     */
    private boolean emailVerified = false;

    /**
     * The user's profile, containing personal information like first name and last name.
     * This is the inverse side of the One-to-One relationship, managed by the 'user' field in {@link UserProfile}.
     * Cascade operations ensure that the profile is saved/deleted along with the user.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private UserProfile profile;

    // --- Helper methods for relationship management ---

    /**
     * A utility method to synchronize both sides of the bidirectional association.
     * It sets the profile for this user and sets this user for the given profile.
     *
     * @param profile The {@link UserProfile} to associate with this user.
     */
    public void linkProfile(UserProfile profile) {
        this.profile = profile;
        if (profile != null) {
            profile.setUser(this);
        }
    }

    // --- UserDetails implementation ---

    /**
     * Returns the authorities granted to the user.
     * The role is prefixed with "ROLE_" as is standard for Spring Security.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be authenticated.
     * @return {@code true} because this application does not implement account expiration.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // Or add a field if expiration is needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * @return {@code true} because this application does not implement credential expiration.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}