package com.readme.sections.service;

import com.readme.sections.dataAccessLayer.NovelCardsDataAccessLayer;
import com.readme.sections.dto.NovelCardsEntityDTO;
import com.readme.sections.dto.NovelCardsViewDTO;
import com.readme.sections.dto.NovelCardsViewDTO.Tag;
import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO.NovelCardsData;
import com.readme.sections.model.NovelCards;
import com.readme.sections.repository.NovelCardsRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelCardsServiceImpl implements NovelCardsService {

    @Value("${spring.data.web.pageable.default-page-size}")
    private int PAGE_SIZE;
    private final NovelCardsDataAccessLayer novelCardsDataAccessLayer;
    private final NovelCardsRepository novelCardsRepository;
    private final ModelMapper modelMapper;

    @Override
    public NovelCardsViewDTO getCards(Long id) {
        NovelCards novelCards = novelCardsRepository.findById(id).get();
        Date startDate = novelCards.getStartDate();
        return NovelCardsViewDTO.builder()
            .novelId(novelCards.getNovelId())
            .title(novelCards.getTitle())
            .author(novelCards.getAuthor())
            .authorComment(novelCards.getAuthorComment())
            .grade(novelCards.getGrade())
            .genre(novelCards.getGenre())
            .tags(novelCards.getTags().stream()
                .map(element -> Tag.builder()
                    .id(element.getId())
                    .name(element.getName())
                    .build()).collect(Collectors.toList()))
            .thumbnail(novelCards.getThumbnail())
            .views(novelCards.getViews())
            .serializationStatus(novelCards.getSerializationStatus())
            .description(novelCards.getDescription())
            .scheduleId(novelCards.getScheduleId())
            .startDate(getUtcToKoreanTime(novelCards.getStartDate()))
            .starRating(novelCards.getStarRating())
            .serializationDays(getSerializationDays(novelCards))
            .newChecking(startDate.compareTo(novelCardsDataAccessLayer.getOneMonthAgo()) >= 0
                && startDate.compareTo(novelCardsDataAccessLayer.getNow()) <= 0)
            .episodeCount(novelCards.getEpisodeCount())
            .build();
    }

    @Override
    public NovelCardsPaginationDTO getAllCardsBySerializationDays(String serializationDays,
        Integer pagination) {
        List<NovelCards> novelCardsList = novelCardsDataAccessLayer.getAllSerializationDays(
            serializationDays, pagination);
        long totalElements = novelCardsDataAccessLayer.getAllSerializationDaysDataCount(
            serializationDays);
        return NovelCardsPaginationDTO.builder()
            .novelCardsData(novelCardsList.stream()
                .map(novelCards -> NovelCardsData.builder()
                    .novelId(novelCards.getNovelId())
                    .title(novelCards.getTitle())
                    .author(novelCards.getAuthor())
                    .grade(novelCards.getGrade())
                    .genre(novelCards.getGenre())
                    .thumbnail(novelCards.getThumbnail())
                    .serializationStatus(novelCards.getSerializationStatus())
                    .description(novelCards.getDescription())
                    .starRating(novelCards.getStarRating())
                    .newChecking(novelCards.getNewChecking())
                    .episodeCount(novelCards.getEpisodeCount())
                    .build())
                .collect(Collectors.toList()))
            .totalElements(totalElements)
            .totalPages((int) Math.ceil((double) totalElements / (double) PAGE_SIZE))
            .build();
    }

    @Override
    public NovelCardsPaginationDTO getAllCardsByGenre(String genre, String serializationStatus,
        Integer pagination) {
        List<NovelCards> novelCardsList = novelCardsDataAccessLayer.getAllGenreData(genre,
            serializationStatus, pagination);
        Long totalElements = novelCardsDataAccessLayer.getAllGenreDataCount(genre,
            serializationStatus);
        return NovelCardsPaginationDTO.builder()
            .novelCardsData(novelCardsList.stream()
                .map(novelCards -> NovelCardsData.builder()
                    .novelId(novelCards.getNovelId())
                    .title(novelCards.getTitle())
                    .author(novelCards.getAuthor())
                    .grade(novelCards.getGrade())
                    .genre(novelCards.getGenre())
                    .thumbnail(novelCards.getThumbnail())
                    .serializationStatus(novelCards.getSerializationStatus())
                    .description(novelCards.getDescription())
                    .starRating(novelCards.getStarRating())
                    .newChecking(novelCards.getNewChecking())
                    .episodeCount(novelCards.getEpisodeCount())
                    .build())
                .collect(Collectors.toList()))
            .totalElements(totalElements)
            .totalPages((int) Math.ceil((double) totalElements / (double) PAGE_SIZE))
            .build();
    }

    @Override
    public void addCards(NovelCardsEntityDTO novelCardsEntityDTO) {
        novelCardsRepository.insert(NovelCards.builder()
            .novelId(novelCardsEntityDTO.getNovelId())
            .title(novelCardsEntityDTO.getTitle())
            .author(novelCardsEntityDTO.getAuthor())
            .authorComment(novelCardsEntityDTO.getAuthorComment())
            .grade(novelCardsEntityDTO.getGrade())
            .genre(novelCardsEntityDTO.getGenre())
            .tags(novelCardsEntityDTO.getTags().stream()
                .map(element -> Tag.builder()
                    .id(element.getId())
                    .name(element.getName())
                    .build()).collect(Collectors.toList()))
            .thumbnail(novelCardsEntityDTO.getThumbnail())
            .views(novelCardsEntityDTO.getViews())
            .serializationStatus(novelCardsEntityDTO.getSerializationStatus())
            .description(novelCardsEntityDTO.getDescription())
            .scheduleId(novelCardsEntityDTO.getScheduleId())
            .startDate(novelCardsEntityDTO.getStartDate())
            .starRating(novelCardsEntityDTO.getStarRating())
            .monday(novelCardsEntityDTO.getMonday())
            .tuesday(novelCardsEntityDTO.getTuesday())
            .wednesday(novelCardsEntityDTO.getWednesday())
            .thursday(novelCardsEntityDTO.getThursday())
            .friday(novelCardsEntityDTO.getFriday())
            .saturday(novelCardsEntityDTO.getSaturday())
            .sunday(novelCardsEntityDTO.getSunday())
            .episodeCount(novelCardsEntityDTO.getEpisodeCount())
            .build());
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
        novelCardsRepository.save(modelMapper.map(novelCardsEntityDTO, NovelCards.class));
    }

    @Override
    public void deleteCards(Long id) {
        novelCardsRepository.deleteById(id);
    }

    @Override
    public List<NovelCardsViewDTO> getNovelCardsForSchedule(Long scheduleId) {
        return novelCardsDataAccessLayer.getAllByScheduleIdData(scheduleId).stream()
            .map(novelCards -> NovelCardsViewDTO.builder()
                .novelId(novelCards.getNovelId())
                .title(novelCards.getTitle())
                .author(novelCards.getAuthor())
                .grade(novelCards.getGrade())
                .genre(novelCards.getGenre())
                .tags(novelCards.getTags().stream()
                    .map(element -> Tag.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .build()).collect(Collectors.toList()))
                .thumbnail(novelCards.getThumbnail())
                .views(novelCards.getViews())
                .serializationStatus(novelCards.getSerializationStatus())
                .description(novelCards.getDescription())
                .scheduleId(novelCards.getScheduleId())
                .startDate(getUtcToKoreanTime(novelCards.getStartDate()))
                .starRating(novelCards.getStarRating())
                .newChecking(novelCards.getNewChecking())
                .episodeCount(novelCards.getEpisodeCount())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public List<NovelCards> searchTags(String tag) {
        return novelCardsRepository.findAllByTagsNameContaining(tag);
    }

    @Override
    public NovelCardsPaginationDTO getNewNovelsByGenre(String genre, Integer pagination) {
        if (pagination == null) {
            pagination = 0;
        }
        List<NovelCards> novelCardsList = novelCardsDataAccessLayer.getNewNovelsData(genre,
            pagination);
        Long totalElements = novelCardsDataAccessLayer.getNewNovelsDataCount(genre);
        return NovelCardsPaginationDTO.builder()
            .novelCardsData(novelCardsList.stream()
                .map(novelCards -> NovelCardsData.builder()
                    .novelId(novelCards.getNovelId())
                    .title(novelCards.getTitle())
                    .author(novelCards.getAuthor())
                    .grade(novelCards.getGrade())
                    .genre(novelCards.getGenre())
                    .thumbnail(novelCards.getThumbnail())
                    .serializationStatus(novelCards.getSerializationStatus())
                    .description(novelCards.getDescription())
                    .starRating(novelCards.getStarRating())
                    .newChecking(novelCards.getNewChecking())
                    .episodeCount(novelCards.getEpisodeCount())
                    .build())
                .collect(Collectors.toList()))
            .totalElements(totalElements)
            .totalPages((int) Math.ceil((double) totalElements / (double) PAGE_SIZE))
            .build();
    }

    private static String getSerializationDays(NovelCards novelCards) {
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

    private static String getUtcToKoreanTime(Date utcTime) {
        SimpleDateFormat koreaFormat = new SimpleDateFormat("yyyy-MM-dd");
        koreaFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return koreaFormat.format(utcTime);
    }
}
