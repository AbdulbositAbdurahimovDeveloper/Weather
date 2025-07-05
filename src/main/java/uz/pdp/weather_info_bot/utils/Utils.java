package uz.pdp.weather_info_bot.utils;

public interface Utils {

    // constants for weather info
    String APE_KEY_1 = "api not found get it from https://www.weatherapi.com/signup.aspx";
    String APE_KEY_2 = "api not found get it from https://www.weatherapi.com/signup.aspx";
    String APE_KEY_3 = "api not found get it from https://www.weatherapi.com/signup.aspx";

    String WEATHER_INFO = "weatherInfo";
    String START = "/start";
    String LANG = "/lang";
    String HELP = "/help";
    String INFO = "/info";
    String CANCEL = "/cancel";
    String SUPER_ADMIN = "/superAdmin";
    String ADMIN = "/admin";
    String USER = "/user";
    String LANGUAGES = """
            <b>Tilni tanlang / Выберите язык / Select your language:</b>
            
            🇺🇿 <b>O‘zbekcha</b> | 🇷🇺 <b>Русский</b> | 🇬🇧 <b>English</b>""";

}
