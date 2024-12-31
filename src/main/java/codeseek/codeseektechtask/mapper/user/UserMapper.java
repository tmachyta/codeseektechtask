package codeseek.codeseektechtask.mapper.user;

import codeseek.codeseektechtask.config.MapperConfig;
import codeseek.codeseektechtask.dto.user.UserRegistrationRequest;
import codeseek.codeseektechtask.dto.user.UserResponseDto;
import codeseek.codeseektechtask.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toModel(UserRegistrationRequest request);
}
