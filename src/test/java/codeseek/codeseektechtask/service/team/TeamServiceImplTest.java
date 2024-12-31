package codeseek.codeseektechtask.service.team;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import codeseek.codeseektechtask.dto.team.CreateTeamRequestDto;
import codeseek.codeseektechtask.dto.team.TeamResponseDto;
import codeseek.codeseektechtask.exception.EntityNotFoundException;
import codeseek.codeseektechtask.mapper.team.TeamMapper;
import codeseek.codeseektechtask.model.Team;
import codeseek.codeseektechtask.repository.team.TeamRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {
    private static final Long TEAM_ID = 1L;
    private static final Long NON_EXISTED_TEAM_ID = 50L;
    private static final String OLD_NAME = "Old";
    private static final String NEW_NAME = "Updated";

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    public void testSuccessfullySaveTeam() {
        CreateTeamRequestDto request = new CreateTeamRequestDto();

        Team teamToSave = new Team();

        when(teamMapper.toModel(request)).thenReturn(teamToSave);

        when(teamRepository.save(teamToSave)).thenReturn(teamToSave);

        TeamResponseDto teamDto = new TeamResponseDto();

        when(teamMapper.toDto(teamToSave)).thenReturn(teamDto);

        TeamResponseDto result = teamService.save(request);
        assertNotNull(result);
    }

    @Test
    public void testGetAllTeams() {
        Team team = new Team();
        Pageable pageable = PageRequest.of(0, 10);
        List<Team> teams = List.of(new Team());
        List<TeamResponseDto> expectedTeams = List.of(new TeamResponseDto());
        Page<Team> page =
                new PageImpl<>(teams, pageable, teams.size());

        when(teamRepository.findAll(pageable)).thenReturn(page);

        when(teamMapper.toDto(team)).thenReturn(new TeamResponseDto());

        List<TeamResponseDto> result = teamService.getAll(pageable);

        Assertions.assertEquals(expectedTeams.size(), result.size());
    }

    @Test
    public void testGetTeamById() {
        Team team = new Team();
        team.setId(TEAM_ID);
        TeamResponseDto teamResponseDto = new TeamResponseDto();
        teamResponseDto.setId(TEAM_ID);

        when(teamRepository.findById(team.getId()))
                .thenReturn(Optional.of(team));

        when(teamMapper.toDto(team)).thenReturn(teamResponseDto);

        TeamResponseDto result = teamService.findById(TEAM_ID);

        Assertions.assertEquals(team.getId(), result.getId());
    }

    @Test
    public void testFindTeamWithNonExistedId() {
        when(teamRepository.findById(NON_EXISTED_TEAM_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> teamService.findById(NON_EXISTED_TEAM_ID));
    }

    @Test
    public void testDeleteTeamById() {
        teamService.deleteById(TEAM_ID);

        when(teamRepository.findById(TEAM_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> teamService.findById(TEAM_ID));
    }

    @Test
    public void testUpdateTeamSuccessfully() {
        CreateTeamRequestDto requestDto = new CreateTeamRequestDto();
        requestDto.setName(NEW_NAME);

        TeamResponseDto expectedResult = new TeamResponseDto();
        expectedResult.setName(NEW_NAME);

        Team team = new Team();
        team.setName(OLD_NAME);

        when(teamRepository.findById(TEAM_ID))
                .thenReturn(Optional.of(team));

        when(teamRepository.save(team)).thenReturn(team);

        when(teamMapper.toDto(team)).thenReturn(expectedResult);

        TeamResponseDto updatedTeam =
                teamService.updateById(TEAM_ID, requestDto);

        Assertions.assertEquals(updatedTeam.getName(), expectedResult.getName());
    }
}
