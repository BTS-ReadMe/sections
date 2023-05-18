package com.readme.sections.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class ScheduleDTO {
    Long id;
    String name;
    LocalDateTime startDate;
    LocalDateTime endDate;

    public String getStringTypeStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(this.startDate);
        return this.startDate.format(formatter);
    }
    public String getStringTypeEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(this.endDate);
        return this.endDate.format(formatter);
    }
}
