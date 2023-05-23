package com.readme.sections.repository;

import com.readme.sections.model.Schedule;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime now, LocalDateTime now2);
}
