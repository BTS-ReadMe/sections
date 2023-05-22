package com.readme.sections.service;

import com.readme.sections.dataAccessLayer.NovelCardsDataAccessLayer;
import com.readme.sections.dto.NovelCardsEntityDTO;
import com.readme.sections.dto.NovelCardsViewDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.enums.SerializationDays;
import com.readme.sections.model.NovelCards;
import com.readme.sections.repository.NovelCardsRepository;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelCardsServiceImpl implements NovelCardsService {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final NovelCardsDataAccessLayer novelCardsDataAccessLayer;
    private final NovelCardsRepository novelCardsRepository;

    @Transactional(readOnly = true)
    @Override
    public NovelCardsViewDTO getCards(Long id) {
        NovelCards novelCards = novelCardsRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
        return new NovelCardsViewDTO(novelCards);
    }

    @Transactional(readOnly = true)
    @Override
    public NovelCardsPaginationDTO getAllCards(Integer pagination) {
        Pageable pageable = PageRequest.of(pagination, PAGE_SIZE);
        return new NovelCardsPaginationDTO(novelCardsRepository.findAll(pageable));
    }

    @Transactional(readOnly = true)
    @Override
    public NovelCardsPaginationDTO getAllCardsBySerializationDays(String serializationDays,
        Integer pagination) {
        return new NovelCardsPaginationDTO(novelCardsDataAccessLayer.findByDayTrue(serializationDays, pagination));
    }

    @Transactional(readOnly = true)
    @Override
    public NovelCardsPaginationDTO getAllCardsByGenre(String genre, String serializationStatus,
        Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        Pageable pageable = PageRequest.of(pagination, PAGE_SIZE);
        return new NovelCardsPaginationDTO(novelCardsRepository.findAllByGenreAndSerializationStatus(genre, serializationStatus, pageable));
    }

    @Transactional
    @Override
    public void addCards(NovelCardsEntityDTO novelCardsEntityDTO) {
        novelCardsRepository.insert(new NovelCards(novelCardsEntityDTO));
    }

    @Override
    public NovelCardsEntityDTO updateNovelCardsDTO(Long id, NovelCardsEntityDTO novelCardsEntityDTO) {
        NovelCards novelCards = novelCardsRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
        return new NovelCardsEntityDTO(novelCards, novelCardsEntityDTO);
    }

    @Transactional
    @Override
    public void updateNovelCardsDTO(NovelCardsEntityDTO novelCardsEntityDTO) {
        novelCardsRepository.save(new NovelCards(novelCardsEntityDTO));
    }

    @Transactional
    @Override
    public void deleteCards(Long id) {
        novelCardsRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<NovelCardsViewDTO> getNovelCardsForSchedule(Long scheduleId) {
        return novelCardsRepository.findAllByScheduleId(scheduleId).stream()
            .map(novelCards -> new NovelCardsViewDTO(novelCards))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public NovelCardsPaginationDTO searchNovelCards(String keyword, Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        Pageable pageable = PageRequest.of(pagination, PAGE_SIZE);
        return new NovelCardsPaginationDTO(novelCardsRepository.findAllByTagsNameOrTitleContaining(keyword, keyword, pageable));
    }

    @Transactional(readOnly = true)
    @Override
    public NovelCardsPaginationDTO getNewNovelsByGenre(String genre, Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        Pageable pageable = PageRequest.of(pagination, PAGE_SIZE);
        return new NovelCardsPaginationDTO(novelCardsRepository.findAllByGenreAndStartDateBetween(genre, getOneMonthAgo(), getNow(), pageable));
    }

    public static String getSerializationDays(NovelCards novelCards) {
        String serializationDays = "";
        if (novelCards.getMonday()) {
            serializationDays += SerializationDays.월.getShortDay() + " ";
        }
        if (novelCards.getTuesday()) {
            serializationDays += SerializationDays.화.getShortDay() + " ";
        }
        if (novelCards.getWednesday()) {
            serializationDays += SerializationDays.수.getShortDay() + " ";
        }
        if (novelCards.getThursday()) {
            serializationDays += SerializationDays.목.getShortDay() + " ";
        }
        if (novelCards.getFriday()) {
            serializationDays += SerializationDays.금.getShortDay() + " ";
        }
        if (novelCards.getSaturday()) {
            serializationDays += SerializationDays.토.getShortDay() + " ";
        }
        if (novelCards.getSunday()) {
            serializationDays += SerializationDays.일.getShortDay() + " ";
        }
        if (serializationDays.equals("")) {
            return serializationDays;
        }
        return serializationDays.substring(0, serializationDays.length() - 1);
    }

    public static String getUtcToKoreanTime(Date utcTime) {
        SimpleDateFormat koreaFormat = new SimpleDateFormat("yyyy-MM-dd");
        koreaFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return koreaFormat.format(utcTime);
    }

    public static boolean checkNewNovel(Date startDate) {
        return startDate.compareTo(getOneMonthAgo()) >= 0
            && startDate.compareTo(getNow()) <= 0;
    }

    public static Date getNow() {
        return new Date();
    }

    public static Date getOneMonthAgo() {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        now = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }
}
