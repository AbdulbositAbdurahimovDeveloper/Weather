package uz.weather.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.weather.payload.request.LoginDTO;
import uz.weather.payload.response.ResponseDTO;
import uz.weather.payload.response.TokenDTO;
import uz.weather.payload.user.UserRegisterRequestDTO;
import uz.weather.payload.user.UserRegisterResponseDTO;
import uz.weather.service.template.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<?>> login(@RequestBody LoginDTO loginDTO) {
        TokenDTO tokenDTO = authService.login(loginDTO);
        return ResponseEntity.ok(ResponseDTO.success(tokenDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<?>> register(@RequestBody @Valid UserRegisterRequestDTO userRegisterRequestDTO) {
        UserRegisterResponseDTO userRegisterResponseDTO = authService.register(userRegisterRequestDTO);
        return ResponseEntity.ok(ResponseDTO.success(userRegisterResponseDTO));
    }
}
