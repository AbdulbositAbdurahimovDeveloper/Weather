package uz.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.weather.payload.request.LoginDTO;
import uz.weather.payload.response.TokenDTO;
import uz.weather.payload.user.UserRegisterRequestDTO;
import uz.weather.payload.user.UserRegisterResponseDTO;
import uz.weather.repository.UserRepository;
import uz.weather.service.template.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO) {
        return null;
    }
}
