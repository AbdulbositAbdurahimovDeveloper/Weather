package uz.pdp.weather_info_bot.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
public class CallbackQueryService {


    public BotApiMethod<?> handleCallback(CallbackQuery callbackQuery) {
        long chatId = callbackQuery.getMessage().getChatId();
        String data = callbackQuery.getData();

        // Bu yerda faqat CallbackQuery bilan bog'liq logika bo'ladi
        switch (data) {
            case "info_button_pressed":
                return new SendMessage(String.valueOf(chatId), "Ma'lumot tugmasi bosildi.");
            case "contact_button_pressed":
                return new SendMessage(String.valueOf(chatId), "Bog'lanish tugmasi bosildi.");
            default:
                return new SendMessage(String.valueOf(chatId), "Noma'lum callback.");
        }
    }
}