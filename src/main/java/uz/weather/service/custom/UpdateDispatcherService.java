package uz.weather.service.custom;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UpdateDispatcherService {

    @Async
    public void dispatch(Update update) {



    }
}
