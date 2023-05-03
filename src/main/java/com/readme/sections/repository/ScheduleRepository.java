package com.readme.sections.repository;

import com.readme.sections.model.Schedule;
import com.readme.sections.projection.ISchedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "select id, name, start_date, end_date \n"
        + "from schedule \n"
        + "where start_date <= now() and now() <= end_date"
    ,nativeQuery = true)
    List<ISchedule> getCurrentSchedules();
}
