package uz.pdp.weather_info_bot.service;

import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.service.weather.WeatherInfoDTO;

public interface LangService {

    String getValue(Language lang, String key);
    String getWeatherResult(Language language, WeatherInfoDTO info, boolean refresh);
}
