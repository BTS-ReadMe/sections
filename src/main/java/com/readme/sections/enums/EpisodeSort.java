package com.readme.sections.enums;

import org.springframework.data.domain.Sort.Direction;

public enum EpisodeSort {
    ASC("1화부터", Direction.ASC),
    DESC("최신순", Direction.DESC);

    private String sortKey;
    private Direction sortValue;

    EpisodeSort(String sortKey, Direction sortValue) {
        this.sortKey = sortKey;
        this.sortValue = sortValue;
    }

    public String getSortKey() {
        return this.sortKey;
    }

    public Direction getSortValue() {
        return this.sortValue;
    }

    public static Direction getSortValueFromKey(String key) {
        for (EpisodeSort es : values()) {
            if (es.sortKey.equals(key)) {
                return es.sortValue;
            }
        }
        throw new IllegalArgumentException("Invalid sortKey: " + key);
    }
}
