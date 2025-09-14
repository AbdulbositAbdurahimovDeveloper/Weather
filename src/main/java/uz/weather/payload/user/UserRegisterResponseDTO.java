package uz.weather.payload.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.weather.enums.Role;

/**
 * Data Transfer Object returned after a successful user registration.
 * It confirms the creation of the user account and includes key metadata
 * like ID and creation timestamp, inherited from base entities.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterResponseDTO {

    // --- User Fields ---
    private Long id;
    private String username;
    private String email;
    private String role;

    // --- UserProfile Fields ---
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bio;
    private Integer profileImageId;

    // --- Status and Audit Fields (for admin or detailed views) ---
    private boolean isEnabled;
    private boolean isAccountNonLocked;
    private boolean isEmailVerified;
    private long createdAt;
    private long updatedAt;
}