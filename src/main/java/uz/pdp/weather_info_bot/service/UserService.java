package uz.pdp.weather_info_bot.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import uz.pdp.weather_info_bot.enums.UserState;
import uz.pdp.weather_info_bot.payload.UserDTO;

public interface UserService {
    UserDTO getOrSave(Message message);

    void updateUserState(Long chatId, UserState userState);

}
