package com.guin.team.adapter.out.persistence;

import com.guin.team.adapter.out.persistence.entity.TeamEntity;
import com.guin.team.adapter.out.persistence.mapper.HashTagMapper;
import com.guin.team.adapter.out.persistence.mapper.TeamMapper;
import com.guin.team.adapter.out.persistence.repository.TeamRepository;
import com.guin.team.application.port.in.command.TeamCommand;
import com.guin.team.domain.vo.Team;
import com.guin.team.application.port.out.TeamPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamPersistenceAdapter implements TeamPort {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final HashTagMapper hashTagMapper;

    @Override
    public Team save(final TeamCommand teamCommand) {
        // TODO: 회원 서버와 연동이되면 그때 저장 가능.
        return teamMapper.toMapper(
                teamRepository.save(new TeamEntity(
                        1L,
                        teamCommand.subject(),
                        teamCommand.content(),
                        teamCommand.subjectType(),
                        teamCommand.openChatUrl(),
                        hashTagMapper.toEntity(teamCommand.hashTags())
                ))
        );
    }
}
