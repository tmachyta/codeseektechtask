package codeseek.codeseektechtask.service.player;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import codeseek.codeseektechtask.dto.player.CreatePlayerRequestDto;
import codeseek.codeseektechtask.dto.player.PlayerResponseDto;
import codeseek.codeseektechtask.exception.EntityNotFoundException;
import codeseek.codeseektechtask.mapper.player.PlayerMapper;
import codeseek.codeseektechtask.model.Player;
import codeseek.codeseektechtask.model.Team;
import codeseek.codeseektechtask.repository.player.PlayerRepository;
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
class PlayerServiceImplTest {
    private static final Long PLAYER_ID = 1L;
    private static final Long TEAM_ID = 1L;
    private static final Long NON_EXISTED_PLAYER_ID = 50L;
    private static final String OLD_NAME = "Old";
    private static final String NEW_NAME = "Updated";
    private static final String OLD_SURNAME = "Old";
    private static final String NEW_SURNAME = "New";
    private static final int OLD_AGE = 18;
    private static final int NEW_AGE = 25;
    private static final int EXPERIENCE = 10;
    private static final int AGE = 25;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerMapper playerMapper;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    public void testSuccessfullySaveTeam() {
        CreatePlayerRequestDto request = new CreatePlayerRequestDto();
        request.setTeamId(TEAM_ID);
        request.setExperience(EXPERIENCE);
        request.setAge(AGE);

        Team team = new Team();
        team.setId(TEAM_ID);

        Player playerToSave = new Player();

        when(teamRepository.findById(TEAM_ID)).thenReturn(Optional.of(team));

        when(playerMapper.toModel(request)).thenReturn(playerToSave);

        playerToSave.setTeam(team);

        when(playerRepository.save(playerToSave)).thenReturn(playerToSave);

        PlayerResponseDto playerResponseDto = new PlayerResponseDto();

        when(playerMapper.toDto(playerToSave)).thenReturn(playerResponseDto);

        PlayerResponseDto result = playerService.save(request);

        assertNotNull(result);
    }

    @Test
    public void testGetAllPlayers() {
        Player player = new Player();
        Pageable pageable = PageRequest.of(0, 10);
        List<Player> players = List.of(new Player());
        List<PlayerResponseDto> expectedTeams = List.of(new PlayerResponseDto());
        Page<Player> page =
                new PageImpl<>(players, pageable, players.size());

        when(playerRepository.findAll(pageable)).thenReturn(page);

        when(playerMapper.toDto(player)).thenReturn(new PlayerResponseDto());

        List<PlayerResponseDto> result = playerService.getAll(pageable);

        Assertions.assertEquals(expectedTeams.size(), result.size());
    }

    @Test
    public void testGetTeamById() {
        Player player = new Player();
        player.setId(PLAYER_ID);

        PlayerResponseDto playerResponseDto = new PlayerResponseDto();
        playerResponseDto.setId(PLAYER_ID);

        when(playerRepository.findById(player.getId()))
                .thenReturn(Optional.of(player));

        when(playerMapper.toDto(player)).thenReturn(playerResponseDto);

        PlayerResponseDto result = playerService.findById(PLAYER_ID);

        Assertions.assertEquals(player.getId(), result.getId());
    }

    @Test
    public void testFindPlayerWithNonExistedId() {
        when(playerRepository.findById(NON_EXISTED_PLAYER_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> playerService.findById(NON_EXISTED_PLAYER_ID));
    }

    @Test
    public void testDeletePlayerById() {
        playerService.deleteById(PLAYER_ID);

        when(playerRepository.findById(PLAYER_ID))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> playerService.findById(PLAYER_ID));

    }
}
