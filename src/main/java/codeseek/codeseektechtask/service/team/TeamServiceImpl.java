package codeseek.codeseektechtask.service.team;

import codeseek.codeseektechtask.dto.team.CreateTeamRequestDto;
import codeseek.codeseektechtask.dto.team.TeamResponseDto;
import codeseek.codeseektechtask.exception.EntityNotFoundException;
import codeseek.codeseektechtask.mapper.team.TeamMapper;
import codeseek.codeseektechtask.model.Team;
import codeseek.codeseektechtask.repository.team.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public TeamResponseDto save(CreateTeamRequestDto request) {
        logger.info("Saving new team with data: {}", request);
        Team team = teamMapper.toModel(request);
        logger.debug("Mapped team entity: {}", team);
        return teamMapper.toDto(teamRepository.save(team));
    }

    @Override
    public List<TeamResponseDto> getAll(Pageable pageable) {
        logger.info("Getting list of teams");
        return teamRepository.findAll(pageable)
                .stream()
                .map(teamMapper::toDto)
                .toList();
    }

    @Override
    public TeamResponseDto findById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find team by id " + id));
        logger.debug("Found team: {}", team);
        return teamMapper.toDto(team);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete team by id");
        teamRepository.deleteById(id);
    }

    @Override
    public TeamResponseDto updateById(Long id, CreateTeamRequestDto request) {
        Team existedTeam = teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find team by id " + id));
        existedTeam.setName(request.getName());
        existedTeam.setBalance(request.getBalance());
        logger.info("Saving updated team with data: {}", existedTeam);
        return teamMapper.toDto(teamRepository.save(existedTeam));
    }
}
