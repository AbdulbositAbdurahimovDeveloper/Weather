package uz.pdp.weather_info_bot.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.pdp.weather_info_bot.MsgMapper.SendMsg;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.UserState;
import uz.pdp.weather_info_bot.payload.LocationDTO;
import uz.pdp.weather_info_bot.payload.UserDTO;
import uz.pdp.weather_info_bot.repository.UserRepository;
import uz.pdp.weather_info_bot.service.LangService;
import uz.pdp.weather_info_bot.service.UserService;
import uz.pdp.weather_info_bot.service.weather.Weather;
import uz.pdp.weather_info_bot.service.weather.WeatherInfoDTO;
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
    private final Weather weather;

    public BotApiMethod<?> handleMessage(Message message) {

        UserDTO user = userService.getOrSave(message.getFrom());

        long chatId = message.getChatId();
        String text = message.getText();
        UserState userState = user.getUserState();
        Language language = user.getLanguage();

        if (text.equals(Utils.START)) {

            return userInputStartCommand(user, language, chatId);

        } else if (text.equals(Utils.LANG)) {

            userService.updateUserState(user.getChatId(), UserState.LANGUAGE_INPUT);
            InlineKeyboardMarkup languageMenu = inlineKeyboardService.getLanguageMenu();
            return sendMessage.sendMessage(chatId, Utils.LANGUAGES, languageMenu);

        } else if (text.equals(langService.getValue(language, "weather_btn"))) {

            LocationDTO location = user.getLocation();
            if (location != null) {

                String value = langService.getValue(language, "no_city_set");
                ReplyKeyboardMarkup replyKeyboardMarkup = replyKeyboardService.getLocationMenu(language);
                return sendMessage.sendMessage(chatId, value, replyKeyboardMarkup);

            } else {

                String city = location.getLat() + ":" + location.getLon();
                WeatherInfoDTO info = weather.info(city);

                if (info != null) {

                    String weatherResult = langService.getWeatherResult(language, info, false);
                    InlineKeyboardMarkup inlineKeyboardMarkup = inlineKeyboardService.getWeatherResult(language,city);


                }


            }


        }

        return null;

    }

    private SendMessage userInputStartCommand(UserDTO user, Language language, long chatId) {
        if (language.equals(Language.none)) {
            userService.updateUserState(user.getChatId(), UserState.LANGUAGE_INPUT);
            InlineKeyboardMarkup languageMenu = inlineKeyboardService.getLanguageMenu();
            return sendMessage.sendMessage(chatId, Utils.LANGUAGES, languageMenu);
        } else {
            userService.updateUserState(user.getChatId(), UserState.LANGUAGE_INPUT);
            String value = langService.getValue(language, "start_user");
            ReplyKeyboardMarkup replyKeyboardMarkup = replyKeyboardService.getMainMenu(language);
            return sendMessage.sendMessage(chatId, value, replyKeyboardMarkup);
        }
    }
}