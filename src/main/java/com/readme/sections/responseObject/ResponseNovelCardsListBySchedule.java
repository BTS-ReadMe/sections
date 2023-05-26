package com.readme.sections.responseObject;

import com.readme.sections.dto.NovelCardsListAndScheduleDTO.NovelCardsBySchedule;
import java.util.List;
import lombok.Getter;

@Getter
public class ResponseNovelCardsListBySchedule {
    private List<NovelCardsBySchedule> novelCardsBySchedules;

    public ResponseNovelCardsListBySchedule(List<NovelCardsBySchedule> novelCardsBySchedules) {
        this.novelCardsBySchedules = novelCardsBySchedules;
    }
}
