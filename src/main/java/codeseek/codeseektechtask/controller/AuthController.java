package codeseek.codeseektechtask.controller;

import codeseek.codeseektechtask.dto.user.UserLoginRequestDto;
import codeseek.codeseektechtask.dto.user.UserLoginResponseDto;
import codeseek.codeseektechtask.dto.user.UserRegistrationRequest;
import codeseek.codeseektechtask.dto.user.UserResponseDto;
import codeseek.codeseektechtask.exception.RegistrationException;
import codeseek.codeseektechtask.security.AuthenticationService;
import codeseek.codeseektechtask.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication management", description = "Endpoints for managing authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login method to authenticate users")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register method to register users")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequest request)
            throws RegistrationException {
        return userService.register(request);
    }
}
