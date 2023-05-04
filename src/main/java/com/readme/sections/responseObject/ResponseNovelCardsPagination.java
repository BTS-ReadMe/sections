package com.readme.sections.responseObject;

import com.readme.sections.dto.NovelCardsPaginationDTO.NovelCardsData;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNovelCardsPagination {
    List<NovelCardsData> novelCardsData;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}