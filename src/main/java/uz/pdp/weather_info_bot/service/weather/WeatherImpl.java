package uz.pdp.weather_info_bot.service.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.weather_info_bot.payload.adaptor.LocaleDateGsonAdapter;
import uz.pdp.weather_info_bot.payload.adaptor.LocaleDateTimeGsonAdapter;
import uz.pdp.weather_info_bot.utils.Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherImpl implements Weather {

    // API kalitlari
    private final String[] apiKeys = {
            Utils.APE_KEY_1,
            Utils.APE_KEY_2,
            Utils.APE_KEY_3,
    };

    // Gson obyektini bir marta yaratib olamiz
    private final Gson gson;
    private final HttpClient httpClient;

    public WeatherImpl() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocaleDateGsonAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocaleDateTimeGsonAdapter(formatter))
                .create();
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Ob-havo ma'lumotlarini API dan oladi va natijani keshlaydi.
     * Agar keshda yaroqli ma'lumot mavjud bo'lsa, API ga murojaat qilinmaydi.
     * Kesh kaliti shahar va kunlar sonidan iborat (masalan, "Tashkent:1").
     *
     * @param city Shahar nomi
     * @param days Prognoz kunlari soni
     * @return Ob-havo ma'lumotlari
     */
    @Override
    @Cacheable(value = "weatherInfo", key = "#city.toLowerCase() + ':' + #days")
    public WeatherInfoDTO info(String city, int days) {
        System.out.printf("ℹ️ Keshda topilmadi. API ga so'rov yuborilmoqda: %s, %d kun%n", city, days);

        for (String apiKey : apiKeys) {
            String url = "https://api.weatherapi.com/v1/forecast.json?q=%s&days=%s&key=%s"
                    .formatted(city, days, apiKey);

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    // Muvaffaqiyatli javobni darhol DTO ga o'girib, qaytaramiz.
                    // Spring bu qaytarilgan qiymatni avtomatik keshga saqlaydi.
                    return gson.fromJson(response.body(), WeatherInfoDTO.class);
                } else if (response.body() != null && response.body().contains("API key has exceeded calls per month")) {
                    System.err.printf("❌ API kalit limiti tugagan! Key: ...%s%n", apiKey.substring(apiKey.length() - 5));
                } else {
                    System.err.printf("⚠️ Noaniq xatolik! Status: %d, URL: %s%n", response.statusCode(), url);
                }

            } catch (IOException | InterruptedException e) {
                System.err.printf("❌ Tarmoqli xatolik yuz berdi. URL: %s, Xato: %s%n", url, e.getMessage());
                // Xatolik bo'lsa, keyingi kalitni sinab ko'rish uchun sikl davom etadi.
            }
        }

        // Agar barcha kalitlar bo'yicha urinish muvaffaqiyatsiz bo'lsa, istisno yuboramiz.
        throw new RuntimeException("⚠️ Barcha API kalitlar bo‘yicha limit tugagan yoki API vaqtinchalik ishlamayapti.");
    }
}