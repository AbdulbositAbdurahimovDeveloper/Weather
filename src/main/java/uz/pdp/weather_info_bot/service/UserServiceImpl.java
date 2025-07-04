package uz.pdp.weather_info_bot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.pdp.weather_info_bot.MsgMapper.LocationMapper;
import uz.pdp.weather_info_bot.MsgMapper.UserMapper;
import uz.pdp.weather_info_bot.enums.Language;
import uz.pdp.weather_info_bot.enums.Role;
import uz.pdp.weather_info_bot.enums.UserState;
import uz.pdp.weather_info_bot.model.User;
import uz.pdp.weather_info_bot.payload.UserDTO;
import uz.pdp.weather_info_bot.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashSet;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LocationMapper locationMapper;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO getOrSave(Message message) {

        User chatId = userRepository.findById((message.getFrom().getId())).orElse(null);
        if (chatId != null) {
            return userMapper.toDto(chatId);
        }

        User user = new User(
                message.getFrom().getId(),
                message.getFrom().getUserName(),
                message.getFrom().getFirstName(),
                message.getFrom().getLastName(),
                Role.USER,
                Role.USER,
                UserState.DEFAULT,
                null,
                new HashSet<>(),
                LocalDateTime.now(),
                new HashSet<>(),
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
}
