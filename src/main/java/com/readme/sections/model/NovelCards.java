package com.readme.sections.model;

import com.readme.sections.dto.NovelCardsViewDTO.Tag;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "novel_cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@CompoundIndex(def = "{'genre': 1, 'serializationStatus': 1}")
public class NovelCards {
    @Id
    Long novelId;
    @Indexed
    String title;
    String description;
    String author;
    String genre;
    Integer grade;
    String thumbnail;
    Date startDate;
    Long views;
    String serializationStatus;
    List<Tag> tags;
    Long scheduleId;
    Double starRating;
    Boolean monday;
    Boolean tuesday;
    Boolean wednesday;
    Boolean thursday;
    Boolean friday;
    Boolean saturday;
    Boolean sunday;
    Boolean newChecking;
    Long episodeCount;

    public double getStarRating() {
        return Math.round(this.starRating * 100.0) / 100.0;
    }
}