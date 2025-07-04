package uz.pdp.weather_info_bot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import uz.pdp.weather_info_bot.enums.Language;

import java.io.IOException;
import java.io.InputStream;

@Service
public class LangServiceImpl implements LangService {

    private static JsonNode translations;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = LangService.class.getClassLoader().getResourceAsStream("language.json");
            translations = objectMapper.readTree(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getValue(Language lang, String key) {
        return translations.has(lang.name()) && translations.get(lang.name()).has(key)
                ? translations.get(lang.name()).get(key).asText()
                : "Noma’lum kalit!";
    }
}
