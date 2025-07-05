package uz.pdp.weather_info_bot.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.Role;
import uz.pdp.weather_info_bot.enums.UserState;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @ManyToOne
    @ToString.Exclude
    private Location location;

    @LastModifiedDate
    private LocalDateTime lastQueryTime;

    private LocalTime notificationTime;

    private boolean isBlocked;


}
