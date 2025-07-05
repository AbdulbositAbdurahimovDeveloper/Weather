package uz.pdp.weather_info_bot.MsgMapper;

import uz.pdp.weather_info_bot.model.Location;
import uz.pdp.weather_info_bot.payload.LocationDTO;

//@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDTO(Location location);
//    Location toEntity(LocationDTO locationDTO);
}