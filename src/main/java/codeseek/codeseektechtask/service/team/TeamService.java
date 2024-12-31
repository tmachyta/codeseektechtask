package codeseek.codeseektechtask.service.team;

import codeseek.codeseektechtask.dto.team.CreateTeamRequestDto;
import codeseek.codeseektechtask.dto.team.TeamResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TeamService {
    TeamResponseDto save(CreateTeamRequestDto request);

    List<TeamResponseDto> getAll(Pageable pageable);

    TeamResponseDto findById(Long id);

    void deleteById(Long id);

    TeamResponseDto updateById(Long id, CreateTeamRequestDto request);
}
