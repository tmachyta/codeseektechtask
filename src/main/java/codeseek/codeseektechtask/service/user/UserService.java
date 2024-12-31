package codeseek.codeseektechtask.service.user;

import codeseek.codeseektechtask.dto.user.UserRegistrationRequest;
import codeseek.codeseektechtask.dto.user.UserResponseDto;
import codeseek.codeseektechtask.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequest request) throws RegistrationException;
}
