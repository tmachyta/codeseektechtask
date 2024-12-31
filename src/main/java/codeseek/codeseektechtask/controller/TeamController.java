package codeseek.codeseektechtask.controller;

import codeseek.codeseektechtask.dto.team.CreateTeamRequestDto;
import codeseek.codeseektechtask.dto.team.TeamResponseDto;
import codeseek.codeseektechtask.service.team.TeamService;
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

@Tag(name = "Teams management", description = "Endpoints for managing Teams")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/teams")
public class TeamController {
    private final TeamService teamService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all teams", description = "Get a list of all available teams")
    public List<TeamResponseDto> findAll(@ParameterObject Pageable pageable) {
        return teamService.getAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get team by id", description = "Get available team by id")
    public TeamResponseDto getTeamById(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Save team to repository",
            description = "Save valid team to repository")
    public TeamResponseDto saveTeam(@RequestBody @Valid CreateTeamRequestDto request) {
        return teamService.save(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete team by id",
            description = "Soft delete of available team by id")
    public void deleteById(@PathVariable Long id) {
        teamService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update player by id", description = "Update available player by id")
    public TeamResponseDto updateById(@PathVariable Long id,
                                      @RequestBody @Valid CreateTeamRequestDto request) {
        return teamService.updateById(id, request);
    }
}
