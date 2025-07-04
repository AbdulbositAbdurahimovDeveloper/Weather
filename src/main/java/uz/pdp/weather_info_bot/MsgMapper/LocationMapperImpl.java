package uz.pdp.weather_info_bot.MsgMapper;

import org.springframework.stereotype.Component;
import uz.pdp.weather_info_bot.model.Location;
import uz.pdp.weather_info_bot.payload.LocationDTO;

@Component
public class LocationMapperImpl
        implements LocationMapper
{
//    @Override
    public LocationDTO toDto(Location location) {
        return new LocationDTO(
                location.getId(),
                location.getName(),
                location.getLat(),
                location.getLon()
        );
    }
}
