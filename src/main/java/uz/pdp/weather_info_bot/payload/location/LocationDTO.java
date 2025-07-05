package uz.pdp.weather_info_bot.payload.location;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDTO {

    private String name;

    private String region;

    private String country;

    private double lat;

    private double lon;

    @SerializedName("tz_id")
    private String tzId;

    @SerializedName("localtime_epoch")
    private String localtimeEpoch;

    private String localtime;


}
