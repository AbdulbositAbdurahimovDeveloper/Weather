package uz.pdp.weather_info_bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.weather_info_bot.enums.Role;

public interface RoleService {
    Role getCurrentRole(Update update);

    Role updateCurrentRole(Update update, String text);

}
