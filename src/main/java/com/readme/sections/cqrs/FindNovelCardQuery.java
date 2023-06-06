package com.readme.sections.cqrs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class FindNovelCardQuery {
    private String novelId;

    public FindNovelCardQuery(String novelId) {
        this.novelId = novelId;
    }
}