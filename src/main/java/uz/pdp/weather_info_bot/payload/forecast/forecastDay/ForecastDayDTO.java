package uz.pdp.weather_info_bot.payload.forecast.forecastDay;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import uz.pdp.weather_info_bot.payload.forecast.forecastDay.astro.AstroDTO;
import uz.pdp.weather_info_bot.payload.forecast.forecastDay.day.DayDTO;
import uz.pdp.weather_info_bot.payload.forecast.forecastDay.hour.Hour;

import java.time.LocalDate;
import java.util.List;


@Data
public class ForecastDayDTO {

    private LocalDate date;

    @SerializedName("date_epoch")
    private Long dateEpoch;

    private DayDTO day;

    private AstroDTO astro;

    private List<Hour> hour;

}
