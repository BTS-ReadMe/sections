package com.readme.sections.service;

import com.readme.sections.dataAccessLayer.EpisodeCardsDataAccessLayer;
import com.readme.sections.dto.EpisodeCardsEntityDTO;
import com.readme.sections.dto.EpisodeCardsPaginationDTO;
import com.readme.sections.model.EpisodeCards;
import com.readme.sections.repository.EpisodeCardsRepository;
import java.util.Calendar;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (pagination == null) {
            episodeCards = episodeCardsDataAccessLayer.findEpisodesByEpisodeCardId(novelId, 0, PAGE_SIZE);
            pagination = 0;
        } else {
            episodeCards = episodeCardsDataAccessLayer.findEpisodesByEpisodeCardId(novelId, pagination * PAGE_SIZE, PAGE_SIZE);
        }
        return new EpisodeCardsPaginationDTO(episodeCards, PAGE_SIZE);
    }

    @Transactional
    @Override
    public void addCards(EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        episodeCardsRepository.insert(new EpisodeCards(episodeCardsEntityDTO));
    }

    @Transactional
    @Override
    public void updateCards(EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        episodeCardsRepository.save(new EpisodeCards(episodeCardsEntityDTO));
    }

    @Override
    public EpisodeCardsEntityDTO existUpdateData(Long id, EpisodeCardsEntityDTO episodeCardsEntityDTO) {
        EpisodeCards episodeCards = episodeCardsRepository.findById(id).get();
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
        return registrationDate.after(calendar.getTime()) &&registrationDate.before(now);
    }
}
