package uz.pdp.weather_info_bot.service.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Condition {

    private String text;
    private String icon;
    int code;

}
