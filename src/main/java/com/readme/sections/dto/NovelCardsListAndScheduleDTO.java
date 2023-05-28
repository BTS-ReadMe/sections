package com.readme.sections.dto;

import com.readme.sections.model.NovelCards;
import com.readme.sections.model.Schedule;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NovelCardsListAndScheduleDTO {
    private long scheduleId;
    private String scheduleName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<NovelCardsBySchedule> novelCardsList;

    @Getter
    @NoArgsConstructor
    public static class NovelCardsBySchedule {
        long novelId;
        String novelTitle;

        public NovelCardsBySchedule(NovelCards novelCards) {
            this.novelId = Long.parseLong(novelCards.getNovelId());
            this.novelTitle = novelCards.getTitle();
        }
    }

    public NovelCardsListAndScheduleDTO(Schedule schedule, List<NovelCards> novelCardsList) {
        this.scheduleId = schedule.getId();
        this.scheduleName = schedule.getName();
        this.startDate = schedule.getStartDate();
        this.endDate = schedule.getEndDate();
        this.novelCardsList = novelCardsList.stream()
            .map(novelCards -> new NovelCardsBySchedule(novelCards))
            .collect(Collectors.toList());
    }
}
