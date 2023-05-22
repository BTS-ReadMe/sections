package com.readme.sections.repository;

import com.readme.sections.model.Schedule;
import com.readme.sections.projection.ISchedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s WHERE s.startDate <= now() AND now() <= s.endDate")
    List<ISchedule> getCurrentSchedules();
}
