package uz.pdp.weather_info_bot.payload.forecast.forecastDay.day;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import uz.pdp.weather_info_bot.service.weather.Condition;

@Data
public class DayDTO {

    @SerializedName("maxtemp_c")
    private double maxTempC;

    @SerializedName("maxtemp_f")
    private double maxTempF;

    @SerializedName("mintemp_c")
    private double minTempC;

    @SerializedName("mintemp_f")
    private double minTempF;

    @SerializedName("avgtemp_c")
    private double avgTempC;

    @SerializedName("avgtemp_f")
    private double avgTempF;

    @SerializedName("maxwind_mph")
    private double maxWindMph;

    @SerializedName("maxwind_kph")
    private double maxWindKph;

    @SerializedName("totalprecip_mm")
    private double totalPrecipMm;

    @SerializedName("totalprecip_in")
    private double totalPrecipIn;

    @SerializedName("totalsnow_cm")
    private double totalSnowCm;

    @SerializedName("avgvis_km")
    private double avgVisibilityKm;

    @SerializedName("avgvis_miles")
    private double avgVisibilityMiles;

    @SerializedName("avghumidity")
    private int avgHumidity;

    @SerializedName("daily_will_it_rain")
    private int dailyWillItRain;

    @SerializedName("daily_chance_of_rain")
    private int dailyChanceOfRain;

    @SerializedName("daily_will_it_snow")
    private int dailyWillItSnow;

    @SerializedName("daily_chance_of_snow")
    private int dailyChanceOfSnow;

    @SerializedName("condition")
    private Condition condition;

    @SerializedName("uv")
    private double uv;

}
