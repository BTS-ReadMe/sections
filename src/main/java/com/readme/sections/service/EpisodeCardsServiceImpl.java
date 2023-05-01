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
    public EpisodeCards getCards(Long id) {
        return episodeCardsRepository.findById(id).get();
    }

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
                    .starRating(episode.getStarRating())
                    .build())
                .collect(Collectors.toList()))
            .build());
    }

    @Override
    public void updateCards(EpisodeCardsDTO episodeCardsDTO) {
        episodeCardsRepository.save(EpisodeCards.builder()
            .novelId(episodeCardsDTO.getNovelId())
            .episodes(episodeCardsDTO.getEpisodes())
            .build());
    }

    @Override
    public EpisodeCardsDTO existUpdateData(Long id, EpisodeCardsDTO episodeCardsDTO) {
        EpisodeCards episodeCards = episodeCardsRepository.findById(id).get();
        return EpisodeCardsDTO.builder()
            .novelId(episodeCardsDTO.getNovelId() != null ? episodeCardsDTO.getNovelId()
                : episodeCards.getNovelId())
            .episodes(episodeCardsDTO.getEpisodes() != null ? episodeCardsDTO.getEpisodes()
                : episodeCards.getEpisodes())
            .build();
    }

    @Override
    public void deleteCards(Long id) {
        episodeCardsRepository.deleteById(id);
    }
}
