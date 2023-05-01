package com.readme.sections.service;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.model.EpisodeCards;
import com.readme.sections.repository.EpisodeCardsRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpisodeCardsServiceImpl implements EpisodeCardsService{
    private final EpisodeCardsRepository episodeCardsRepository;
    @Override
    public void addCards(EpisodeCardsDTO episodeCardsDTO) {
        episodeCardsRepository.insert(EpisodeCards.builder()
            .novelId(episodeCardsDTO.getNovelId())
            .episodes(episodeCardsDTO.getEpisodes().stream()
                .map(episode -> EpisodeCards.Episode.builder()
                    .id(episode.getId())
                    .name(episode.getName())
                    .free(episode.getFree())
                    .registrationDate(episode.getRegistrationDate())
                    .build())
                .collect(Collectors.toList()))
            .build());
    }
}
