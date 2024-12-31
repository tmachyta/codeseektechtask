package codeseek.codeseektechtask.controller;

import codeseek.codeseektechtask.dto.player.CreatePlayerRequestDto;
import codeseek.codeseektechtask.dto.player.PlayerResponseDto;
import codeseek.codeseektechtask.service.player.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Players management", description = "Endpoints for managing Players")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/players")
public class PlayerController {
    private final PlayerService playerService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all players", description = "Get a list of all available players")
    public List<PlayerResponseDto> findAll(@ParameterObject Pageable pageable) {
        return playerService.getAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get player by id", description = "Get available player by id")
    public PlayerResponseDto getPlayerById(@PathVariable Long id) {
        return playerService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Save player to repository",
            description = "Save valid player to repository")
    public PlayerResponseDto savePlayer(@RequestBody @Valid CreatePlayerRequestDto request) {
        return playerService.save(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete player by id",
            description = "Soft delete of available player by id")
    public void deleteById(@PathVariable Long id) {
        playerService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update player by id", description = "Update available player by id")
    public PlayerResponseDto updateById(@PathVariable Long id,
                                        @RequestBody @Valid CreatePlayerRequestDto request) {
        return playerService.updateById(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/transfer/{id}")
    @Operation(summary = "transfer player by id", description = "transfer available player by id")
    public PlayerResponseDto transferById(@PathVariable Long id,
                                        @RequestBody @Valid CreatePlayerRequestDto request) {
        return playerService.transferPlayerById(id, request);
    }
}
