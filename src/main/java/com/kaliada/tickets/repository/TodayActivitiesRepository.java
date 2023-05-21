package com.kaliada.tickets.repository;

import com.kaliada.tickets.entity.TodayActivities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayActivitiesRepository extends JpaRepository<TodayActivities, Long> {
}
