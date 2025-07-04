package uz.pdp.weather_info_bot.bot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Setter
@Getter
@RequiredArgsConstructor
public class MyTelegramBot extends TelegramWebhookBot {

    private String botToken;
    private String botUsername;
    private String botPath;

    private final UpdateDispatcherService dispatcherService;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        if (update.hasMessage()) {
            log.info("New message from: {}, chat_id: {}, with text: {}",
                    update.getMessage().getFrom().getUserName(),
                    update.getMessage().getChatId(),
                    update.getMessage().getText());
        } else if (update.hasCallbackQuery()) {
            log.info("New callback from: {}, with data: {}",
                    update.getCallbackQuery().getFrom().getUserName(),
                    update.getCallbackQuery().getData());
        }

        return dispatcherService.dispatch(update);
    }
}