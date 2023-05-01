package com.readme.sections.service;

import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.dto.EpisodeCardsDTO.Episode;
import com.readme.sections.model.EpisodeCards;
import com.readme.sections.repository.EpisodeCardsRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpisodeCardsServiceImpl implements EpisodeCardsService{
    private final EpisodeCardsRepository episodeCardsRepository;
    private final ModelMapper modelMapper;
    @Override
    public void addCards(EpisodeCardsDTO episodeCardsDTO) {
        episodeCardsRepository.insert(modelMapper.map(episodeCardsDTO, EpisodeCards.class));
    }
}
