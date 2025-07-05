package uz.pdp.weather_info_bot.payload.forecast.forecastDay.hour;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import uz.pdp.weather_info_bot.service.weather.Condition;

@Data
public class Hour {

    @SerializedName("time_epoch")
    private long timeEpoch;

    @SerializedName("time")
    private String time;

    @SerializedName("temp_c")
    private double tempC;

    @SerializedName("temp_f")
    private double tempF;

    @SerializedName("is_day")
    private int isDay;

    @SerializedName("condition")
    private Condition condition;

    @SerializedName("wind_mph")
    private double windMph;

    @SerializedName("wind_kph")
    private double windKph;

    @SerializedName("wind_degree")
    private int windDegree;

    @SerializedName("wind_dir")
    private String windDir;

    @SerializedName("pressure_mb")
    private double pressureMb;

    @SerializedName("pressure_in")
    private double pressureIn;

    @SerializedName("precip_mm")
    private double precipMm;

    @SerializedName("precip_in")
    private double precipIn;

    @SerializedName("snow_cm")
    private double snowCm;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("cloud")
    private int cloud;

    @SerializedName("feelslike_c")
    private double feelsLikeC;

    @SerializedName("feelslike_f")
    private double feelsLikeF;

    @SerializedName("windchill_c")
    private double windChillC;

    @SerializedName("windchill_f")
    private double windChillF;

    @SerializedName("heatindex_c")
    private double heatIndexC;

    @SerializedName("heatindex_f")
    private double heatIndexF;

    @SerializedName("dewpoint_c")
    private double dewPointC;

    @SerializedName("dewpoint_f")
    private double dewPointF;

    @SerializedName("will_it_rain")
    private int willItRain;

    @SerializedName("chance_of_rain")
    private int chanceOfRain;

    @SerializedName("will_it_snow")
    private int willItSnow;

    @SerializedName("chance_of_snow")
    private int chanceOfSnow;

    @SerializedName("vis_km")
    private double visibilityKm;

    @SerializedName("vis_miles")
    private double visibilityMiles;

    @SerializedName("gust_mph")
    private double gustMph;

    @SerializedName("gust_kph")
    private double gustKph;

    @SerializedName("uv")
    private double uv;

}
