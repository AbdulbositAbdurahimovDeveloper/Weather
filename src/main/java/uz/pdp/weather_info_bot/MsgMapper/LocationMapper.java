package uz.pdp.weather_info_bot.MsgMapper;

import org.mapstruct.Mapper;
import uz.pdp.weather_info_bot.model.Location;
import uz.pdp.weather_info_bot.payload.LocationDTO;

//@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDto(Location location);
//    Location toEntity(LocationDTO locationDTO);
}