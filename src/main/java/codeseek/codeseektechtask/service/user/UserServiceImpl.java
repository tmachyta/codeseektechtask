package codeseek.codeseektechtask.service.user;

import codeseek.codeseektechtask.dto.user.UserRegistrationRequest;
import codeseek.codeseektechtask.dto.user.UserResponseDto;
import codeseek.codeseektechtask.exception.RegistrationException;
import codeseek.codeseektechtask.mapper.user.UserMapper;
import codeseek.codeseektechtask.model.Role;
import codeseek.codeseektechtask.model.Role.RoleName;
import codeseek.codeseektechtask.model.User;
import codeseek.codeseektechtask.repository.user.UserRepository;
import codeseek.codeseektechtask.service.role.RoleService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserResponseDto register(UserRegistrationRequest request) throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }

        if (!request.getPassword().equals(request.getRepeatPassword())) {
            throw new RegistrationException("Passwords do not match");
        }

        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role userRole = roleService.getRoleByRoleName(RoleName.USER);
        user.setRoles(new HashSet<>(Set.of(userRole)));
        return userMapper.toDto(userRepository.save(user));
    }
}
