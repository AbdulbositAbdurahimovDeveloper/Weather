package uz.pdp.weather_info_bot.MsgMapper;

import org.springframework.stereotype.Component;
import uz.pdp.weather_info_bot.model.User;
import uz.pdp.weather_info_bot.payload.UserDTO;

@Component
public class UserMapperImpl
        implements UserMapper {

    private final LocationMapper locationMapper;

    public UserMapperImpl(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    //    @Override
    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getChatId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getUserState(),
                user.getUserCurrentRole(),
                user.getLanguage(),
                user.getLocation() != null ? locationMapper.toDTO(user.getLocation()) : null,
                user.getLastQueryTime(),
                user.getNotificationTime(),
                user.isBlocked()
        );
    }
}
