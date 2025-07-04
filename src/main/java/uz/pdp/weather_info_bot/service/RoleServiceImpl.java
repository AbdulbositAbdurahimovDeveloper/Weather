package uz.pdp.weather_info_bot.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.pdp.weather_info_bot.enums.Role;
import uz.pdp.weather_info_bot.model.User;
import uz.pdp.weather_info_bot.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public Role getCurrentRole(Update update) {
        Long chatId = getChatIdFromUpdate(update);
        return userRepository.findByChatIdOrThrow(chatId).getUserCurrentRole();
    }

    @Override
    public Role updateCurrentRole(Update update, String command) { // parametr nomini "command" deb o'zgartirdim

        // Yordamchi metod orqali chatId'ni olamiz
        Long chatId = getChatIdFromUpdate(update);
        User user = userRepository.findByChatIdOrThrow(chatId);

        // 1. Komandani tozalash va validatsiya qilish
        if (command == null || !command.startsWith("/") || command.length() <= 1) {
            throw new IllegalArgumentException("Invalid role command format. Expected format: /rolename");
        }

        // Komandadan rol nomini ajratib olamiz: "/admin" -> "admin" -> "ADMIN"
        String roleName = command.substring(1).toUpperCase();

        // 2. String'ni Role enum'iga aylantirish
        Role targetRole;
        try {
            targetRole = Role.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            // Agar /notexist kabi noma'lum komanda kelsa
            throw new IllegalArgumentException("Unknown role command: " + command);
        }

        // 3. Asosiy logika: Foydalanuvchining roli yangi rolni o'rnatishga ruxsati bormi?
        Role baseRole = user.getRole();
        if (baseRole.canManage(targetRole)) {
            user.setUserCurrentRole(targetRole);
            userRepository.save(user);
        } else {
            // Ruxsat yo'q bo'lsa, xatolik qaytaramiz
            throw new SecurityException(
                    "User with role " + baseRole + " is not allowed to change role to " + targetRole
            );
        }

        return user.getUserCurrentRole();
    }

    // Yordamchi metod (avvalgidek)
    private Long getChatIdFromUpdate(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        throw new IllegalStateException("Cannot extract user ID from the update.");
    }
}
