//package com.readme.sections.cqrs;
//
//import com.readme.sections.model.NovelCards.Tag;
//import java.util.Date;
//import java.util.List;
//import lombok.Getter;
//import org.axonframework.modelling.command.TargetAggregateIdentifier;
//
//@Getter
//public class CreateNovelCardCommand {
//    @TargetAggregateIdentifier
//    private String novelId;
//    private String title;
//    private String description;
//    private String author;
//    private String authorComment;
//    private String genre;
//    private Integer grade;
//    private String thumbnail;
//    private Date startDate;
//    private Long views;
//    private String serializationStatus;
//    private List<Tag> tags;
//    private Long scheduleId;
//    private Double starRating;
//    private Boolean monday;
//    private Boolean tuesday;
//    private Boolean wednesday;
//    private Boolean thursday;
//    private Boolean friday;
//    private Boolean saturday;
//    private Boolean sunday;
//    private Long episodeCount;
//}