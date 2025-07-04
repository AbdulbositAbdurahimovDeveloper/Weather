package uz.pdp.weather_info_bot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.weather_info_bot.enums.Role;
import uz.pdp.weather_info_bot.service.users.CallbackQueryService;
import uz.pdp.weather_info_bot.service.users.MessageService;
import uz.pdp.weather_info_bot.service.RoleService;
import uz.pdp.weather_info_bot.utils.Utils;

@Service
@RequiredArgsConstructor
public class UpdateDispatcherService {

    private final MessageService messageService;
    private final CallbackQueryService callbackQueryService;
    private final RoleService roleService;

    public BotApiMethod<?> dispatch(Update update) {

        Role currentRole = Role.USER;

//        currentRole = roleService.getCurrentRole(update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if (text.equals(Utils.ADMIN) || text.equals(Utils.USER) || text.equals(Utils.SUPER_ADMIN)) {
                currentRole = roleService.updateCurrentRole(update, text);
            }
        }

        if (currentRole == Role.SUPER_ADMIN) {

            if (update.hasMessage()) {
//                return messageService.handleMessage(update.getMessage());
            } else if (update.hasCallbackQuery()) {
//                return callbackQueryService.handleCallback(update.getCallbackQuery());
            }

        } else if (currentRole == Role.ADMIN) {

            if (update.hasMessage()) {
//                return messageService.handleMessage(update.getMessage());
            } else if (update.hasCallbackQuery()) {
//                return callbackQueryService.handleCallback(update.getCallbackQuery());
            }

        } else {

            if (update.hasMessage()) {
                return messageService.handleMessage(update.getMessage());
            } else if (update.hasCallbackQuery()) {
                return callbackQueryService.handleCallback(update.getCallbackQuery());
            }
            // Kelajakda boshqa turdagi updatelarni ham qo'shish mumkin
            // else if (update.hasInlineQuery()) { ... }
            // else if (update.hasEditedMessage()) { ... }

        }
        return null;
    }
}