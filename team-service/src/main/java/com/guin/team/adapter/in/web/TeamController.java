package com.guin.team.adapter.in.web;

import com.guin.team.adapter.in.web.dto.request.TeamCreateRequest;
import com.guin.team.adapter.in.web.dto.response.TeamCreateResponse;
import com.guin.team.domain.vo.Team;
import com.guin.team.port.in.TeamUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamUseCase teamUseCase;

    @PostMapping
    public ResponseEntity<TeamCreateResponse> createTeam(@Valid @RequestBody TeamCreateRequest request) {
        final Team team = teamUseCase.save(request);

        return ResponseEntity.created(URI.create("/team/%d".formatted(team.id())))
                .body(new TeamCreateResponse(
                        team.id(),
                        team.openChatUrl(),
                        team.content(),
                        team.subject(),
                        team.subjectType(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList()
                ));
    }

}