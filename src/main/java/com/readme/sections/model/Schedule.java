package com.readme.sections.model;

import com.readme.sections.dto.ScheduleDTO;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "schedule")
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "start_date", nullable = false)
    LocalDateTime startDate;
    @Column(name = "end_date", nullable = false)
    LocalDateTime endDate;
    @CreatedDate
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public Schedule(ScheduleDTO scheduleDTO) {
        this.id = scheduleDTO.getId();
        this.name = scheduleDTO.getName();
        this.startDate = scheduleDTO.getStartDate();
        this.endDate = scheduleDTO.getEndDate();
    }
}
