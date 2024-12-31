package codeseek.codeseektechtask.mapper.team;

import codeseek.codeseektechtask.config.MapperConfig;
import codeseek.codeseektechtask.dto.team.CreateTeamRequestDto;
import codeseek.codeseektechtask.dto.team.TeamResponseDto;
import codeseek.codeseektechtask.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TeamMapper {
    TeamResponseDto toDto(Team team);

    @Mapping(target = "id", ignore = true)
    Team toModel(CreateTeamRequestDto request);
}
