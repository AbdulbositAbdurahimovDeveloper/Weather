package uz.pdp.weather_info_bot.model;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.Role;
import uz.pdp.weather_info_bot.enums.UserState;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "users")
public class User {

    @Id
    private Long chatId;

    private String username;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Enumerated(EnumType.STRING)
    private Role userCurrentRole = Role.USER;

    @Enumerated(EnumType.STRING)
    private UserState userState;

    @Enumerated(EnumType.STRING)
    private Language language;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_locations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations;

    private LocalDateTime lastQueryTime;

    @ElementCollection(fetch = FetchType.EAGER) // EAGER, chunki vaqtlar odatda user bilan birga kerak bo'ladi
    @CollectionTable(
            name = "user_notification_times", // Yangi yordamchi jadval nomi
            joinColumns = @JoinColumn(name = "user_id") // Bu jadvalni 'users' jadvaliga bog'laydigan ustun
    )
    @Column(name = "notification_time") // Vaqtlarning o'zi saqlanadigan ustun nomi
    private Set<LocalTime> notificationTimes;

    private boolean isBlocked;


}
