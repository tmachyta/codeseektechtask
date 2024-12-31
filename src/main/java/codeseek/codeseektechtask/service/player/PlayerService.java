package codeseek.codeseektechtask.service.player;

import codeseek.codeseektechtask.dto.player.CreatePlayerRequestDto;
import codeseek.codeseektechtask.dto.player.PlayerResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PlayerService {
    PlayerResponseDto save(CreatePlayerRequestDto request);

    List<PlayerResponseDto> getAll(Pageable pageable);

    PlayerResponseDto findById(Long id);

    void deleteById(Long id);

    PlayerResponseDto updateById(Long id, CreatePlayerRequestDto request);

    PlayerResponseDto transferPlayerById(Long id, CreatePlayerRequestDto request);
}
