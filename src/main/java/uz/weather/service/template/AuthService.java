package uz.weather.service.template;

import uz.weather.payload.request.LoginDTO;
import uz.weather.payload.response.TokenDTO;
import uz.weather.payload.user.UserRegisterRequestDTO;
import uz.weather.payload.user.UserRegisterResponseDTO;

public interface AuthService {
    TokenDTO login(LoginDTO loginDTO);

    UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO);

}
