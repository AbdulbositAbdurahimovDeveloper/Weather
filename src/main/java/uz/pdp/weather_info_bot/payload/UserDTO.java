package uz.pdp.weather_info_bot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.Role;
import uz.pdp.weather_info_bot.enums.UserState;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

/**
 * DTO for {@link uz.pdp.weather_info_bot.model.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Long chatId;
    private String username;
    private String firstName;
    private String lastName;
    private Role role;
    private UserState userState;
    private Role userCurrentRole = Role.USER;
    private Language language;
    private LocationDTO location;
    private LocalDateTime lastQueryTime;
    private LocalTime notificationTime;
    private boolean isBlocked;
}