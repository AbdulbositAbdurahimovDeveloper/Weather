package uz.pdp.weather_info_bot.utils.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import uz.pdp.weather_info_bot.enums.Language;

public interface InlineKeyboardService {

    InlineKeyboardMarkup getLanguageMenu();

    InlineKeyboardMarkup getWeatherResult(Language language, String city);
}
