package com.readme.sections.service;

import com.readme.sections.dataAccessLayer.NovelCardsDataAccessLayer;
import com.readme.sections.dto.NovelCardsEntityDTO;
import com.readme.sections.dto.NovelCardsViewDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO.NovelCardsData;
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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelCardsServiceImpl implements NovelCardsService {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final NovelCardsDataAccessLayer novelCardsDataAccessLayer;
    private final NovelCardsRepository novelCardsRepository;

    @Override
    public NovelCardsViewDTO getCards(Long id) {
        NovelCards novelCards = novelCardsRepository.findById(id).get();
        return new NovelCardsViewDTO(novelCards);
    }

    @Override
    public NovelCardsPaginationDTO getAllCardsBySerializationDays(String serializationDays,
        Integer pagination) {
        return new NovelCardsPaginationDTO(novelCardsDataAccessLayer.findByDayTrue(serializationDays, pagination));
    }

    @Override
    public NovelCardsPaginationDTO getAllCardsByGenre(String genre, String serializationStatus,
        Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        Pageable pageable = PageRequest.of(pagination, PAGE_SIZE);
        return new NovelCardsPaginationDTO(novelCardsRepository.findAllByGenreAndSerializationStatus(genre, serializationStatus, pageable));
    }

    @Override
    public void addCards(NovelCardsEntityDTO novelCardsEntityDTO) {
        novelCardsRepository.insert(new NovelCards(novelCardsEntityDTO));
    }

    @Override
    public NovelCardsEntityDTO existUpdateData(Long id, NovelCardsEntityDTO novelCardsEntityDTO) {
        NovelCards novelCards = novelCardsRepository.findById(id).get();
        return NovelCardsEntityDTO.builder()
            .novelId(novelCards.getNovelId())
            .title(novelCardsEntityDTO.getTitle() != null ? novelCardsEntityDTO.getTitle()
                : novelCards.getTitle())
            .description(
                novelCardsEntityDTO.getDescription() != null ? novelCardsEntityDTO.getDescription()
                    : novelCards.getDescription())
            .author(novelCardsEntityDTO.getAuthor() != null ? novelCardsEntityDTO.getAuthor()
                : novelCards.getAuthor())
            .authorComment(novelCardsEntityDTO.getAuthorComment() != null ? novelCardsEntityDTO.getAuthorComment()
                : novelCards.getAuthorComment())
            .genre(novelCardsEntityDTO.getGenre() != null ? novelCardsEntityDTO.getGenre()
                : novelCards.getGenre())
            .grade(novelCardsEntityDTO.getGrade() != null ? novelCardsEntityDTO.getGrade()
                : novelCards.getGrade())
            .thumbnail(
                novelCardsEntityDTO.getThumbnail() != null ? novelCardsEntityDTO.getThumbnail()
                    : novelCards.getThumbnail())
            .startDate(
                novelCardsEntityDTO.getStartDate() != null ? novelCardsEntityDTO.getStartDate()
                    : novelCards.getStartDate())
            .views(novelCardsEntityDTO.getViews() != null ? novelCardsEntityDTO.getViews()
                : novelCards.getViews())
            .serializationStatus(novelCardsEntityDTO.getSerializationStatus() != null
                ? novelCardsEntityDTO.getSerializationStatus()
                : novelCards.getSerializationStatus())
            .tags(novelCardsEntityDTO.getTags() != null ? novelCardsEntityDTO.getTags()
                : novelCards.getTags())
            .scheduleId(
                novelCardsEntityDTO.getScheduleId() != null ? novelCardsEntityDTO.getScheduleId()
                    : novelCards.getScheduleId())
            .starRating(
                novelCardsEntityDTO.getStarRating() != null ? novelCardsEntityDTO.getStarRating()
                    : novelCards.getStarRating())
            .monday(
                novelCardsEntityDTO.getMonday() != null ? novelCardsEntityDTO.getMonday()
                    : novelCards.getMonday())
            .tuesday(
                novelCardsEntityDTO.getTuesday() != null ? novelCardsEntityDTO.getTuesday()
                    : novelCards.getTuesday())
            .wednesday(
                novelCardsEntityDTO.getWednesday() != null ? novelCardsEntityDTO.getWednesday()
                    : novelCards.getWednesday())
            .thursday(
                novelCardsEntityDTO.getThursday() != null ? novelCardsEntityDTO.getThursday()
                    : novelCards.getThursday())
            .friday(
                novelCardsEntityDTO.getFriday() != null ? novelCardsEntityDTO.getFriday()
                    : novelCards.getFriday())
            .saturday(
                novelCardsEntityDTO.getSaturday() != null ? novelCardsEntityDTO.getSaturday()
                    : novelCards.getSaturday())
            .sunday(
                novelCardsEntityDTO.getSunday() != null ? novelCardsEntityDTO.getSunday()
                    : novelCards.getSunday())
            .episodeCount(novelCardsEntityDTO.getEpisodeCount() != null
                ? novelCardsEntityDTO.getEpisodeCount()
                : novelCards.getEpisodeCount())
            .build();
    }

    @Override
    public void updateCards(NovelCardsEntityDTO novelCardsEntityDTO) {
        novelCardsRepository.save(new NovelCards(novelCardsEntityDTO));
    }

    @Override
    public void deleteCards(Long id) {
        novelCardsRepository.deleteById(id);
    }

    @Override
    public List<NovelCardsViewDTO> getNovelCardsForSchedule(Long scheduleId) {
        return novelCardsDataAccessLayer.getAllByScheduleIdData(scheduleId).stream()
            .map(novelCards -> new NovelCardsViewDTO(novelCards))
            .collect(Collectors.toList());
    }

    @Override
    public NovelCardsPaginationDTO searchNovelCards(String keyword, Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        Pageable pageable = PageRequest.of(pagination, PAGE_SIZE);
        return new NovelCardsPaginationDTO(novelCardsRepository.findAllByTagsNameOrTitleContaining(keyword, keyword, pageable));
    }

    @Override
    public NovelCardsPaginationDTO getNewNovelsByGenre(String genre, Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        Pageable pageable = PageRequest.of(pagination, PAGE_SIZE);
        return new NovelCardsPaginationDTO(novelCardsRepository.findAllByStartDateBetween(getOneMonthAgo(), getNow(), pageable));
    }

    public static String getSerializationDays(NovelCards novelCards) {
        String serializationDays = "";
        if (novelCards.getMonday() != null && novelCards.getMonday() != null) {
            serializationDays += "월 ";
        }
        if (novelCards.getTuesday() != null && novelCards.getTuesday() != null) {
            serializationDays += "화 ";
        }
        if (novelCards.getWednesday() != null && novelCards.getWednesday() != null) {
            serializationDays += "수 ";
        }
        if (novelCards.getThursday() != null && novelCards.getThursday() != null) {
            serializationDays += "목 ";
        }
        if (novelCards.getFriday() != null && novelCards.getFriday() != null) {
            serializationDays += "금 ";
        }
        if (novelCards.getSaturday() != null && novelCards.getSaturday() != null) {
            serializationDays += "토 ";
        }
        if (novelCards.getSunday() != null && novelCards.getSunday() != null) {
            serializationDays += "일 ";
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
