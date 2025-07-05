package uz.pdp.weather_info_bot.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.pdp.weather_info_bot.MsgMapper.SendMsg;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.Role;
import uz.pdp.weather_info_bot.enums.UserState;
import uz.pdp.weather_info_bot.payload.UserDTO;
import uz.pdp.weather_info_bot.service.LangService;
import uz.pdp.weather_info_bot.service.UserService;
import uz.pdp.weather_info_bot.utils.buttons.ReplyKeyboardService;

@Service
@RequiredArgsConstructor
public class CallbackQueryService {


    private final UserService userService;
    private final LangService langService;
    private final SendMsg sendMsg;
    private final ReplyKeyboardService replyKeyboardService;

    public BotApiMethod<?> handleCallback(CallbackQuery callbackQuery) {

        UserDTO user = userService.getOrSave(callbackQuery.getFrom());

        long chatId = callbackQuery.getMessage().getChatId();
        String data = callbackQuery.getData();
        UserState userState = user.getUserState();
        String queryData = callbackQuery.getData();

        if (userState.equals(UserState.LANGUAGE_INPUT)) {

            Language language = Language.valueOf(queryData);
            userService.updateLang(chatId, language);
            userService.updateUserState(chatId, userState);
            String value = langService.getValue(language, "lang");
            ReplyKeyboardMarkup replyKeyboardMarkup = replyKeyboardService.getMainMenu(language);
            return sendMsg.sendMessage(chatId, value, replyKeyboardMarkup);

        }
        return null;
    }
}