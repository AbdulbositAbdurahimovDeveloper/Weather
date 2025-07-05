package uz.pdp.weather_info_bot.payload.forecast.forecastDay.astro;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AstroDTO {


    private String sunrise;

    private String sunset;

    private String moonrise;

    @SerializedName("moonset")
    private String moonSet;

    @SerializedName("moon_phase")
    private String moonPhase;

    @SerializedName("moon_illumination")
    private int moonIllumination;

    @SerializedName("is_moon_up")
    private int isMoonUp;

    @SerializedName("is_sun_up")
    private int isSunUp;

}
