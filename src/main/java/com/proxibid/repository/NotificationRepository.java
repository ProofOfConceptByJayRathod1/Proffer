package com.proxibid.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proxibid.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	boolean existsByUserIdAndEventId(String userId, Long eventId);

	@Query(value = "SELECT * FROM notification n WHERE n.notify_at = CURRENT_DATE", nativeQuery = true)
	List<Notification> findTodaysAlerts();

	@Query(value = "SELECT * FROM notification n WHERE n.user_id = ?1 AND n.notify_date = CURRENT_DATE AND n.notify_time < ?2", nativeQuery = true)
	List<Notification> findTodaysByUserId(String userId, LocalTime now);
	
	

	@Query(value = "SELECT * FROM notification n WHERE n.user_id = ?1 AND n.notify_date < CURRENT_DATE", nativeQuery = true)
	List<Notification> findPastNotificationsByUserId(String userId);

}
