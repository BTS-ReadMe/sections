package com.readme.sections.service;

import com.readme.sections.dataAccessLayer.EpisodeCardsDataAccessLayer;
import com.readme.sections.dto.EpisodeCardsEntityDTO;
import com.readme.sections.dto.EpisodeCardsPaginationDTO;
import com.readme.sections.dto.EpisodeDTO;
import com.readme.sections.model.EpisodeCards;
import com.readme.sections.model.EpisodeCards.Episode;
import com.readme.sections.repository.EpisodeCardsRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodeCardsServiceImpl implements EpisodeCardsService {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final EpisodeCardsRepository episodeCardsRepository;
    private final EpisodeCardsDataAccessLayer episodeCardsDataAccessLayer;

    @Transactional(readOnly = true)
    @Override
    public EpisodeCardsPaginationDTO getCards(Long novelId, Integer pagination) {
        EpisodeCards episodeCards = new EpisodeCards();
        log.info(episodeCardsRepository.findAll().toString());
        if (pagination == null) {
            episodeCards = episodeCardsDataAccessLayer.findEpisodesByEpisodeCardId(novelId, 0,
                PAGE_SIZE);
            pagination = 0;
        } else {
            episodeCards = episodeCardsDataAccessLayer.findEpisodesByEpisodeCardId(novelId,
                pagination * PAGE_SIZE, PAGE_SIZE);
        }
        return new EpisodeCardsPaginationDTO(episodeCards, PAGE_SIZE, pagination);
    }

    @Transactional
    @Override
    public void addEpisode(EpisodeDTO episodeDTO) {
        episodeCardsDataAccessLayer.addEpisode(episodeDTO);
    }

    @Transactional
    @Override
    public void addCards(EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        try {
            episodeCardsRepository.insert(new EpisodeCards(episodeCardsEntityDTO));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @Transactional
    @Override
    public void updateEpisode(EpisodeDTO episodeDTO) {
        episodeCardsDataAccessLayer.updateEpisode(episodeDTO);
    }

    @Transactional
    @Override
    public void updateCards(EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        episodeCardsRepository.save(new EpisodeCards(episodeCardsEntityDTO));
    }

    @Override
    public EpisodeCardsEntityDTO existUpdateData(Long id,
        EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        EpisodeCards episodeCards = episodeCardsRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return new EpisodeCardsEntityDTO(episodeCards, episodeCardsEntityDTO);
    }

    @Transactional
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
        return registrationDate.after(calendar.getTime()) && registrationDate.before(now);
    }
}
