package uz.pdp.weather_info_bot.utils.buttons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.service.LangService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InlineKeyboardServiceImpl implements InlineKeyboardService {

    private final LangService langService;

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

    @Override
    public InlineKeyboardMarkup getWeatherResult(Language language, String city) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        // 1-qator: [btn_hourly][changecity]
        List<InlineKeyboardButton> row1 = new ArrayList<>();

        InlineKeyboardButton btnHourly = new InlineKeyboardButton();
        btnHourly.setText(langService.getValue(language, "btn_hourly_forecast"));
        btnHourly.setCallbackData("hourlyForecast_" + city);
        row1.add(btnHourly);

        InlineKeyboardButton btnChangeCity = new InlineKeyboardButton();
        btnChangeCity.setText(langService.getValue(language, "btn_change_city"));
        btnChangeCity.setCallbackData("changeCity");
        row1.add(btnChangeCity);

        rows.add(row1);

        // 2-qator: [refresh][delete][ulashish]
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        InlineKeyboardButton btnRefresh = new InlineKeyboardButton();
        btnRefresh.setText(langService.getValue(language, "btn_refresh"));
        btnRefresh.setCallbackData("refresh_" + city);
        row2.add(btnRefresh);

        InlineKeyboardButton btnDelete = new InlineKeyboardButton();
        btnDelete.setText(langService.getValue(language, "btn_delete"));  // "delete"
        btnDelete.setCallbackData("delete");
        row2.add(btnDelete);

        InlineKeyboardButton btnShare = new InlineKeyboardButton();
        btnShare.setText(langService.getValue(language, "btn_share")); // "Ulashish"
        btnShare.setSwitchInlineQuery(""); // Inline query rejimiga o‘tkazadi
        row2.add(btnShare);

        rows.add(row2);

        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }
}
