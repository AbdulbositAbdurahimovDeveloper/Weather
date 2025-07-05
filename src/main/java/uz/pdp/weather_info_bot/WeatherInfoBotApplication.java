package uz.pdp.weather_info_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherInfoBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherInfoBotApplication.class, args);
    }

}
