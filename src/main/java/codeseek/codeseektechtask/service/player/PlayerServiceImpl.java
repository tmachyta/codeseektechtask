package codeseek.codeseektechtask.service.player;

import codeseek.codeseektechtask.dto.player.CreatePlayerRequestDto;
import codeseek.codeseektechtask.dto.player.PlayerResponseDto;
import codeseek.codeseektechtask.exception.EntityNotFoundException;
import codeseek.codeseektechtask.mapper.player.PlayerMapper;
import codeseek.codeseektechtask.model.Player;
import codeseek.codeseektechtask.model.Team;
import codeseek.codeseektechtask.repository.player.PlayerRepository;
import codeseek.codeseektechtask.repository.team.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
    private static final int DEFAULT_NUMBER = 100000;
    private static final int DEFAULT_SECOND_NUMBER = 100;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final TeamRepository teamRepository;

    @Override
    public PlayerResponseDto save(CreatePlayerRequestDto request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find team by id "
                + request.getTeamId()));

        logger.info("Saving new player with data: {}", request);
        Player player = playerMapper.toModel(request);
        player.setTransferValue((request.getExperience() * DEFAULT_NUMBER)
                / request.getAge());
        player.setTeam(team);
        logger.debug("Mapped player entity: {}", player);
        return playerMapper.toDto(playerRepository.save(player));
    }

    @Override
    public List<PlayerResponseDto> getAll(Pageable pageable) {
        logger.info("Getting list of players");
        return playerRepository.findAll(pageable)
                .stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @Override
    public PlayerResponseDto findById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find player by id " + id));
        logger.debug("Found player: {}", player);
        return playerMapper.toDto(player);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Delete team by id");
        playerRepository.deleteById(id);
    }

    @Override
    public PlayerResponseDto updateById(Long id, CreatePlayerRequestDto request) {
        Player existedPlayer = playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find player by id " + id));
        existedPlayer.setName(request.getName());
        existedPlayer.setSurname(request.getSurname());
        existedPlayer.setAge(request.getAge());
        logger.info("Saving updated player with data: {}", existedPlayer);
        return playerMapper.toDto(existedPlayer);
    }

    @Override
    public PlayerResponseDto transferPlayerById(Long id, CreatePlayerRequestDto request) {
        Player existedPlayer = playerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find player by id " + id));
        logger.debug("Found player: {}", existedPlayer);

        Team newTeam = teamRepository.findById(request.getTeamId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find team by id "
                        + request.getTeamId()));
        logger.debug("Found team: {}", newTeam);

        Team oldTeam = existedPlayer.getTeam();
        logger.debug("Found old team: {}", oldTeam);

        int transferValue = existedPlayer.getTransferValue();
        int commission = transferValue * (oldTeam.getTransferCommission() / DEFAULT_SECOND_NUMBER);
        int totalCost = transferValue + commission;

        logger.debug("Calculated total cost: {}", totalCost);

        if (newTeam.getBalance() < totalCost) {
            throw new RuntimeException("Team does not have enough balance for the transfer");
        }

        newTeam.setBalance(newTeam.getBalance() - totalCost);
        oldTeam.setBalance(oldTeam.getBalance() + totalCost);
        teamRepository.save(newTeam);
        teamRepository.save(oldTeam);
        existedPlayer.setTeam(newTeam);
        return playerMapper.toDto(playerRepository.save(existedPlayer));
    }
}
