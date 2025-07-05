package uz.pdp.weather_info_bot.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.UserState;
import uz.pdp.weather_info_bot.payload.UserDTO;

public interface UserService {
    UserDTO getOrSave(User message);

    void updateUserState(Long chatId, UserState userState);

    void updateLang(long chatId, Language language);
}
