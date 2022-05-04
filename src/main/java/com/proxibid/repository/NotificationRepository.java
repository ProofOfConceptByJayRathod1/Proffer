package com.proxibid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proxibid.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query(value = "SELECT * FROM notification n WHERE n.notify_at = CURRENT_DATE", nativeQuery = true)
	List<Notification> findTodaysAlerts();

	boolean existsByUserIdAndEventId(String userId, Long eventId);

}
