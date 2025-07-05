package uz.pdp.weather_info_bot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.weather_info_bot.MsgMapper.LocationMapper;
import uz.pdp.weather_info_bot.MsgMapper.UserMapper;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.Role;
import uz.pdp.weather_info_bot.enums.UserState;
import uz.pdp.weather_info_bot.model.User;
import uz.pdp.weather_info_bot.payload.UserDTO;
import uz.pdp.weather_info_bot.repository.UserRepository;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LocationMapper locationMapper;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO getOrSave(org.telegram.telegrambots.meta.api.objects.User fromUser) {

        User chatId = userRepository.findById((fromUser.getId())).orElse(null);
        if (chatId != null) {
            return userMapper.toDto(chatId);
        }

        User user = new User(
                fromUser.getId(),
                fromUser.getUserName(),
                fromUser.getFirstName(),
                fromUser.getLastName(),
                Role.USER,
                Role.USER,
                UserState.DEFAULT,
                Language.none,
                null,
                LocalDateTime.now(),
                null,
                false
        );

        userRepository.save(user);

        return userMapper.toDto(user);

    }


    @Override
    public void updateUserState(Long chatId, UserState userState) {
        User user = userRepository.findByChatIdOrThrow(chatId);
        user.setUserState(userState);
        userRepository.save(user);
    }

    @Override
    public void updateLang(long chatId, Language language) {
        User user = userRepository.findByChatIdOrThrow(chatId);
        user.setLanguage(language);
        userRepository.save(user);
    }
}
