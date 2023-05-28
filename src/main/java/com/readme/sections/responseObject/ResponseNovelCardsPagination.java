package com.readme.sections.responseObject;

import com.readme.sections.dto.NovelCardsPaginationDTO;
import com.readme.sections.dto.NovelCardsPaginationDTO.NovelCardsData;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseNovelCardsPagination {
    private List<NovelCardsData> novelCardsData;
    private long totalElements;
    private int totalPages;
    private int page;

    public ResponseNovelCardsPagination(NovelCardsPaginationDTO novelCardsPaginationDTO) {
        this.novelCardsData = novelCardsPaginationDTO.getNovelCardsData();
        this.totalElements = novelCardsPaginationDTO.getTotalElements();
        this.totalPages = novelCardsPaginationDTO.getTotalPages();
        this.page = novelCardsPaginationDTO.getPage();
    }
}
