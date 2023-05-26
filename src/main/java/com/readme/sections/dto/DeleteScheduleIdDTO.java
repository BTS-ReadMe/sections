package com.readme.sections.dto;

import lombok.Getter;

@Getter
public class DeleteScheduleIdDTO {
    private Long novelCardsId;

    public DeleteScheduleIdDTO(Long novelCardsId) {
        this.novelCardsId = novelCardsId;
    }
}
