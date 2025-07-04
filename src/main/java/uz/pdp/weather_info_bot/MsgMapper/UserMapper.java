package uz.pdp.weather_info_bot.MsgMapper;

import org.mapstruct.Mapper;
import uz.pdp.weather_info_bot.model.User;
import uz.pdp.weather_info_bot.payload.UserDTO;

//@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface UserMapper {

    UserDTO toDto(User user);

//    User toEntity(UserDTO dto);
}
