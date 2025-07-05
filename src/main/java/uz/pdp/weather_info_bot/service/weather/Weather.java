package uz.pdp.weather_info_bot.service.weather;

public interface Weather {
    WeatherInfoDTO info(String city, int days);

    default WeatherInfoDTO info(String city) {
        return this.info(city, 1);
    }
}
