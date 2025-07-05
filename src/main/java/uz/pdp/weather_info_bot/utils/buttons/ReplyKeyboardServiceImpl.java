package uz.pdp.weather_info_bot.utils.buttons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.service.LangService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyKeyboardServiceImpl implements ReplyKeyboardService {

    private final LangService langService;

    @Override
    public ReplyKeyboardMarkup getMainMenu(Language language) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); // Klaviatura o'lchamini moslashtiradi
        replyKeyboardMarkup.setSelective(true); // Faqat shu foydalanuvchiga ko'rinadi

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        // 1-qator: Asosiy funksiya
        KeyboardRow row1 = new KeyboardRow();
        row1.add(langService.getValue(language, "weather_btn")); // Masalan: "🌤 Ob-havo ma'lumoti"
        keyboardRows.add(row1);

        // 2-qator: Joylashuv bilan bog'liq amallar
        KeyboardRow row2 = new KeyboardRow();
        // Yangi tugma: "Joriy shahar"
        row2.add(langService.getValue(language, "current_city_btn")); // Masalan: "📍 Joriy shahar"
        row2.add(langService.getValue(language, "other_city_btn")); // Masalan: "🏙 Boshqa shahar"
        keyboardRows.add(row2);

        // 3-qator: Sozlamalar va bildirishnomalar
        KeyboardRow row3 = new KeyboardRow();
        row3.add(langService.getValue(language, "settings_btn")); // Masalan: "⚙️ Sozlamalar"
        row3.add(langService.getValue(language, "notify_btn")); // Masalan: "🔔 Bildirishnomalar"
        keyboardRows.add(row3);

        // 4-qator: Yordamchi tugmalar
        KeyboardRow row4 = new KeyboardRow();
        row4.add(langService.getValue(language, "help_btn")); // Masalan: "ℹ️ Yordam"
        row4.add(langService.getValue(language, "contact_btn")); // Masalan: "📞 Biz bilan bog'lanish"
        keyboardRows.add(row4);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }

    @Override
    public ReplyKeyboardMarkup getLocationMenu(Language language) {
        return null;
    }
}
