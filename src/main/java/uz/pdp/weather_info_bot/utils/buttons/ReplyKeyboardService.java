package uz.pdp.weather_info_bot.utils.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.pdp.weather_info_bot.enums.Language;

public interface ReplyKeyboardService {

    ReplyKeyboardMarkup getMainMenu(Language language);

    ReplyKeyboardMarkup getLocationMenu(Language language);
}
