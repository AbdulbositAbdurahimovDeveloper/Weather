package uz.pdp.weather_info_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import uz.pdp.weather_info_bot.exception.EntityNotFoundException;
import uz.pdp.weather_info_bot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByChatIdOrThrow(Long chatId) {
        return findById(chatId).orElseThrow(() -> new EntityNotFoundException("User not found", HttpStatus.NOT_FOUND));
    }
    default User findByChatIdOrThrow(String chatId) {
        return findById(Long.parseLong(chatId)).orElseThrow(() -> new EntityNotFoundException("User not found", HttpStatus.NOT_FOUND));
    }

}