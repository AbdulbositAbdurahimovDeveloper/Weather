package uz.pdp.weather_info_bot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.pdp.weather_info_bot.service.RoleService;

@Configuration
public class TelegramBotConfig {

    @Value("${telegrambots.bots[0].token}")
    private String botToken;

    @Value("${telegrambots.bots[0].username}")
    private String botUsername;

    @Value("${telegrambots.webhook-path}")
    private String path;

    // Bean yaratish metodiga BotService parametrini qo'shamiz
    // Spring bu parametrni avtomatik ravishda o'zi topib, uzatadi
    @Bean
    public MyTelegramBot myTelegramBot(UpdateDispatcherService updateDispatcherService) {
        MyTelegramBot bot = new MyTelegramBot(updateDispatcherService);
        bot.setBotToken(botToken);
        bot.setBotUsername(botUsername);
        bot.setBotPath(path);
        return bot;
    }
}