package com.readme.sections.responseObject;

import com.readme.sections.dto.NovelCardsListAndScheduleDTO;
import com.readme.sections.dto.NovelCardsListAndScheduleDTO.NovelCardsAndSchedule;
import com.readme.sections.service.ScheduleServiceImpl;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseNovelCardsLIstAndSchedule {
    private long scheduleId;
    private String scheduleName;
    private String startDate;
    private String endDate;
    private List<NovelCardsAndSchedule> novelCardsList;

    public ResponseNovelCardsLIstAndSchedule(
        NovelCardsListAndScheduleDTO novelCardsListAndScheduleDTO) {
        this.scheduleId = novelCardsListAndScheduleDTO.getScheduleId();
        this.scheduleName = novelCardsListAndScheduleDTO.getScheduleName();
        this.startDate = ScheduleServiceImpl.getUtcToKoreanTime(novelCardsListAndScheduleDTO.getStartDate());
        this.endDate = ScheduleServiceImpl.getUtcToKoreanTime(novelCardsListAndScheduleDTO.getEndDate());
        this.novelCardsList = novelCardsListAndScheduleDTO.getNovelCardsList();
    }
}
