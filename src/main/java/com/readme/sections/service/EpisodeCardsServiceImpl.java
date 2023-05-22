package com.readme.sections.service;

import com.readme.sections.dataAccessLayer.EpisodeCardsDataAccessLayer;
import com.readme.sections.dto.EpisodeCardsDTO;
import com.readme.sections.model.EpisodeCards;
import com.readme.sections.model.EpisodeCards.Episode;
import com.readme.sections.repository.EpisodeCardsRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodeCardsServiceImpl implements EpisodeCardsService {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final EpisodeCardsRepository episodeCardsRepository;
    private final EpisodeCardsDataAccessLayer episodeCardsDataAccessLayer;

    @Override
    public EpisodeCardsDTO getCards(Long novelId, Integer pagination) {
        EpisodeCards episodeCards = new EpisodeCards();
        if (pagination == null) {
            episodeCards = episodeCardsDataAccessLayer.findEpisodesByEpisodeCardId(novelId, 0, PAGE_SIZE);
            pagination = 0;
        } else {
            episodeCards = episodeCardsDataAccessLayer.findEpisodesByEpisodeCardId(novelId, pagination * PAGE_SIZE, PAGE_SIZE);
        }
        return EpisodeCardsDTO.builder()
            .novelId(episodeCards.getNovelId())
            .episodes(episodeCards.getEpisodes())
            .page(pagination)
            .size(PAGE_SIZE)
            .totalElements(episodeCards.getEpisodeCount())
            .totalPages(
                (int) Math.ceil((double) episodeCards.getEpisodeCount() / (double) PAGE_SIZE))
            .build();
    }

    @Override
    public void addCards(EpisodeCardsDTO episodeCardsDTO) {
        episodeCardsRepository.insert(new EpisodeCards(episodeCardsDTO));
    }

    @Override
    public void updateCards(EpisodeCardsDTO episodeCardsDTO) {
        episodeCardsRepository.save(new EpisodeCards(episodeCardsDTO));
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

    public static boolean checkIsNew(Date registrationDate) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        now = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        return registrationDate.after(calendar.getTime()) &&registrationDate.before(now);
    }
}
