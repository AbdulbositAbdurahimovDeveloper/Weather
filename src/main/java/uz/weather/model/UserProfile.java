package uz.weather.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.weather.model.Abs.AbsLongEntity;

import java.util.Objects;

/**
 * Stores additional personal information for a {@link User}.
 * <p>
 * This entity has a strict one-to-one relationship with the {@link User} entity and is the owning side of the relationship.
 * The lifecycle of a UserProfile is tied to its parent User.
 * It also supports soft deletion.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "user")
@Entity
@Table(name = "user_profiles")
@SQLDelete(sql = "UPDATE user_profiles SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted=false")
public class UserProfile extends AbsLongEntity {

    /**
     * The user's first name.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * The user's last name.
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * The user's contact phone number. Optional.
     */
    private String phoneNumber;

    /**
     * A short biography or description of the user. Optional.
     */
    @Column(columnDefinition = "TEXT")
    private String bio;

    /**
     * The parent {@link User} to whom this profile belongs.
     * This is the owning side of the relationship. The {@code user_id} foreign key cannot be null.
     * FetchType is LAZY to prevent unnecessary joins when loading a UserProfile.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    /**
     * A flag for soft deletion. If true, the profile is considered deleted.
     */
    private boolean deleted = false;
}