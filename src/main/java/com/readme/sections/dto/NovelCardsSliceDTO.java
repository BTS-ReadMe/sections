package com.readme.sections.dto;

import com.readme.sections.dto.NovelCardsPaginationDTO.NovelCardsData;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NovelCardsSliceDTO {
    List<NovelCardsData> novelCardsData;
    private int page;
    private int size;
    private boolean next;
}
