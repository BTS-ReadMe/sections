package com.readme.sections.service;

import com.readme.sections.dto.NovelCardsListAndScheduleDTO;
import com.readme.sections.dto.NovelCardsListAndScheduleDTO.NovelCardsBySchedule;
import com.readme.sections.dto.ScheduleDTO;
import com.readme.sections.dto.UpdateScheduleIdListDTO;
import java.util.List;

public interface ScheduleService {
    public ScheduleDTO getSchedule(Long id);
    public List<ScheduleDTO> getSchedules();
    public List<NovelCardsListAndScheduleDTO> getNovelCardsListAndSchedule();
    public List<NovelCardsBySchedule> getNovelCardsListBySchedule(Long scheduleId);
    public void addSchedule(ScheduleDTO scheduleDTO);
    public void updateScheduleIdInNovelList(Long scheduleId, UpdateScheduleIdListDTO updateScheduleIdListDTOList);
    public ScheduleDTO existUpdateData(Long id, ScheduleDTO scheduleDTO);
    public void updateSchedule(ScheduleDTO scheduleDTO);
    public void deleteSchedule(Long id);
}
