package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsListAndScheduleDTO;
import com.readme.sections.dto.NovelCardsListAndScheduleDTO.NovelCardsBySchedule;
import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.dto.UpdateScheduleIdListDTO;
import com.readme.sections.model.NovelCards;
import com.readme.sections.model.Schedule;
import com.readme.sections.repository.NovelCardsRepository;
import com.readme.sections.repository.ScheduleRepository;
import com.readme.sections.requestObject.RequestNovelId;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final NovelCardsRepository novelCardsRepository;

    @Transactional(readOnly = true)
    @Override
    public ScheduleDTO getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
        return new ScheduleDTO(schedule);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleDTO> getSchedules() {
        return scheduleRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime.now(), LocalDateTime.now()).stream()
            .map(schedule -> new ScheduleDTO(schedule))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<NovelCardsListAndScheduleDTO> getNovelCardsListAndSchedule() {
        return scheduleRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime.now(), LocalDateTime.now()).stream()
            .map(schedule -> new NovelCardsListAndScheduleDTO(schedule, novelCardsRepository.findAllByScheduleId(schedule.getId())))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<NovelCardsBySchedule> getNovelCardsListBySchedule(Long scheduleId) {
        return novelCardsRepository.findAllByScheduleId(scheduleId).stream()
            .map(novelCards -> new NovelCardsBySchedule(novelCards))
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.save(new Schedule(scheduleDTO));
    }

    @Transactional
    @Override
    public void updateScheduleIdInNovelList(Long scheduleId, UpdateScheduleIdListDTO updateScheduleIdListDTOList) {
        for (RequestNovelId requestNovelId : updateScheduleIdListDTOList.getRequestNovelIdList()) {
            NovelCards novelCards = novelCardsRepository.findById(requestNovelId.getNovelId()).get();
            novelCards.setScheduleId(scheduleId);
            novelCardsRepository.save(novelCards);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ScheduleDTO existUpdateData(Long id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
        return new ScheduleDTO(schedule, scheduleDTO);
    }

    @Transactional
    @Override
    public void updateSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.save(new Schedule(scheduleDTO));
    }

    @Transactional
    @Override
    public void deleteSchedule(Long id) {
        try {
            scheduleRepository.deleteById(id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public static String getUtcToKoreanTime(LocalDateTime utcTime) {
        ZonedDateTime koreanTime = utcTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return formatter.format(koreanTime);
    }

}
