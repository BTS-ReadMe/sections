package com.readme.sections.cqrs;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelCardsCommandService {
    private final CommandGateway commandGateway;

    public void createNovelCard(CreateNovelCardCommand command) {
        commandGateway.send(command);
    }
}
