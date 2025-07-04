package uz.pdp.weather_info_bot.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import uz.pdp.weather_info_bot.MsgMapper.SendMsg;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.UserState;
import uz.pdp.weather_info_bot.payload.UserDTO;
import uz.pdp.weather_info_bot.repository.UserRepository;
import uz.pdp.weather_info_bot.service.LangService;
import uz.pdp.weather_info_bot.service.UserService;
import uz.pdp.weather_info_bot.utils.Utils;
import uz.pdp.weather_info_bot.utils.buttons.InlineKeyboardService;
import uz.pdp.weather_info_bot.utils.buttons.ReplyKeyboardService;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final SendMsg sendMessage;
    private final LangService langService;
    private final InlineKeyboardService inlineKeyboardService;
    private final ReplyKeyboardService replyKeyboardService;

    public BotApiMethod<?> handleMessage(Message message) {

        UserDTO user = userService.getOrSave(message);

        long chatId = message.getChatId();
        String text = message.getText();
        UserState userState = user.getUserState();
        Language language = user.getLanguage();

        if (language == null){

            InlineKeyboardMarkup languageMenu = inlineKeyboardService.getLanguageMenu();
            return sendMessage.sendMessage(chatId, Utils.LANGUAGES, languageMenu);


        }


        if (text.equals(Utils.START)) {

            userService.updateUserState(user.getChatId(), UserState.LANGUAGE_INPUT);
            String value = langService.getValue(language, "start_user");
            return sendMessage.sendMessage(chatId, value);

        } else if (text.equals(Utils.LANG)){

            userService.updateUserState(user.getChatId(), UserState.LANGUAGE_INPUT);
            InlineKeyboardMarkup languageMenu = inlineKeyboardService.getLanguageMenu();
            return sendMessage.sendMessage(chatId, Utils.LANGUAGES, languageMenu);

        }

        return null;

    }
}