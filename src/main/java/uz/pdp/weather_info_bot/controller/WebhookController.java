package uz.pdp.weather_info_bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.weather_info_bot.bot.MyTelegramBot;

@RestController
public class WebhookController {

    private final MyTelegramBot telegramBot;

    public WebhookController(MyTelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostMapping("${telegrambots.webhook-path}")
    public ResponseEntity<?> onUpdateReceived(@RequestBody Update update) {
        BotApiMethod<?> response = telegramBot.onWebhookUpdateReceived(update);
        
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.ok().build();
    }
}