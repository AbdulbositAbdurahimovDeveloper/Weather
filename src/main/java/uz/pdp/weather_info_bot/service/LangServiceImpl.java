package uz.pdp.weather_info_bot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.service.weather.Condition;
import uz.pdp.weather_info_bot.service.weather.WeatherInfoDTO;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LangServiceImpl implements LangService {

    private static final DateTimeFormatter DATE_ONLY_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private static JsonNode translations;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = LangService.class.getClassLoader().getResourceAsStream("language.json");
            translations = objectMapper.readTree(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getValue(Language lang, String key) {
        return translations.has(lang.name()) && translations.get(lang.name()).has(key)
                ? translations.get(lang.name()).get(key).asText()
                : "Noma’lum kalit!";
    }

    @Override
    public String getWeatherResult(Language language, WeatherInfoDTO info, boolean refresh) {

        String value = this.getValue(language, "weather_result");


        String locationName = info.getLocation().getName();

        String date = refresh
                ? DATE_TIME_FORMATTER.format(LocalDateTime.now())
                : DATE_ONLY_FORMATTER.format(info.getForecast().getForecastDay().get(0).getDate());

        double avgTemp = info.getForecast().getForecastDay().get(0).getDay().getAvgTempC();
        double minTemp = info.getForecast().getForecastDay().get(0).getDay().getMinTempC();
        double maxTemp = info.getForecast().getForecastDay().get(0).getDay().getMaxTempC();
        double windSpeed = info.getForecast().getForecastDay().get(0).getDay().getMaxWindKph();
        double humidity = info.getForecast().getForecastDay().get(0).getDay().getAvgHumidity();
        double rainChance = info.getForecast().getForecastDay().get(0).getDay().getDailyChanceOfRain();
        double uvIndex = info.getForecast().getForecastDay().get(0).getDay().getUv();
        Condition condition = info.getCurrent().getCondition();
        String sunrise = info.getForecast().getForecastDay().get(0).getAstro().getSunrise();
        String sunset = info.getForecast().getForecastDay().get(0).getAstro().getSunset();
        String conditionText = condition.getText();

        return value.formatted(
                locationName,  // 1: Ob-havo joyi
                date,          // 2: Sana
                avgTemp,       // 3: Harorat
                minTemp,       // 4: min
                maxTemp,       // 5: max
                windSpeed,     // 6: Shamol
                humidity,      // 7: Namlik
                rainChance,    // 8: Yomg‘ir ehtimoli
                sunrise,       // 9: Quyosh chiqishi
                sunset,        //10: Quyosh botishi
                uvIndex,       //11: UV
                conditionText //12: Holat
        );

    }
}
