package uz.pdp.weather_info_bot.service;

import uz.pdp.weather_info_bot.enums.Language;

public interface LangService {

    String getValue(Language lang, String key);
}
