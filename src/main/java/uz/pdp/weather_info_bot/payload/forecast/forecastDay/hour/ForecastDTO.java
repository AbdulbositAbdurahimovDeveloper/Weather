package uz.pdp.weather_info_bot.payload.forecast.forecastDay.hour;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.weather_info_bot.payload.forecast.forecastDay.ForecastDayDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForecastDTO {

    @SerializedName("forecastday")
    private List<ForecastDayDTO> forecastDay;

}
