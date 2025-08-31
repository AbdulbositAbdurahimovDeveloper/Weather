package uz.weather.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {

    private JwtProperties jwt = new JwtProperties();
    private CacheProperties cache = new CacheProperties();
    private TelegramProperties telegram = new TelegramProperties();
    private String serverIp;
    private String domain;

    // Har bir guruh uchun alohida klasslar
    @Getter
    @Setter
    public static class JwtProperties {
        private String secret;
        private String accessTokenExpiration;
        private String refreshTokenExpiration;
    }

    @Getter
    @Setter
    public static class CacheProperties {
        private int temporaryProcessTtlSeconds;
    }
    
    @Getter
    @Setter
    public static class TelegramProperties {
        private BotProperties bot = new BotProperties();
        
        @Getter
        @Setter
        public static class BotProperties {
            private String token;
            private String username;
            private String channelId;
        }
    }
}