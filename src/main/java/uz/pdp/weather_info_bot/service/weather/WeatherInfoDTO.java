package uz.pdp.weather_info_bot.service.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.weather_info_bot.payload.current.CurrentDTO;
import uz.pdp.weather_info_bot.payload.forecast.forecastDay.hour.ForecastDTO;
import uz.pdp.weather_info_bot.payload.location.LocationDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherInfoDTO {

    private LocationDTO location;
    private CurrentDTO current;
    private ForecastDTO forecast;

}
