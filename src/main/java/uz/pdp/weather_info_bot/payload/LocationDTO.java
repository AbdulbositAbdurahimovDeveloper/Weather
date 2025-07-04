package uz.pdp.weather_info_bot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.weather_info_bot.model.Location;

import java.io.Serializable;

/**
 * DTO for {@link Location}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO implements Serializable {
    private Long id;
    private String name;
    private Double lat;
    private Double lon;
}