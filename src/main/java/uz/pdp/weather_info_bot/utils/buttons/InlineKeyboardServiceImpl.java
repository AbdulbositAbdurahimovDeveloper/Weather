package uz.pdp.weather_info_bot.utils.buttons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.weather_info_bot.enums.Language;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InlineKeyboardServiceImpl implements InlineKeyboardService {

    @Override
    public InlineKeyboardMarkup getLanguageMenu() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("\uD83C\uDDFA\uD83C\uDDFF O‘zbekcha");
        button1.setCallbackData(Language.uz.name());
        row.add(button1);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("\uD83C\uDDEC\uD83C\uDDE7 English");
        button2.setCallbackData(Language.en.name());
        row.add(button2);

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("\uD83C\uDDF7\uD83C\uDDFA Русский");
        button3.setCallbackData(Language.ru.name());
        row.add(button3);

        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);

        return inlineKeyboardMarkup;
    }
}
