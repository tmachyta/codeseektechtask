package codeseek.codeseektechtask.mapper.player;

import codeseek.codeseektechtask.config.MapperConfig;
import codeseek.codeseektechtask.dto.player.CreatePlayerRequestDto;
import codeseek.codeseektechtask.dto.player.PlayerResponseDto;
import codeseek.codeseektechtask.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PlayerMapper {
    @Mapping(source = "team.id", target = "teamId")
    PlayerResponseDto toDto(Player player);

    @Mapping(target = "id", ignore = true)
    Player toModel(CreatePlayerRequestDto request);
}
