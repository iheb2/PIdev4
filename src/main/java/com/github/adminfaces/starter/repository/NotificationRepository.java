package com.github.adminfaces.starter.repository;


import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import com.github.adminfaces.starter.infra.model.*;
import com.github.adminfaces.starter.model.Notification;
import com.github.adminfaces.starter.model.TypeNotification;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long>{

	@Query("from Notification order by send_date asc")
	public List<Notification> orderByAscendingSentDate();
	
	@Query("from Notification order by send_date desc")
	public List<Notification> orderByDescendingSentDate();
	
	@Query("select n from Notification n where n.send_date between :min and :max")
	public List<Notification> Range(@Param("min") Date min, @Param("max") Date max);
	
	@Query("SELECT c FROM Notification c WHERE c.type_notification= :type_notification")
	List<Notification> SearchNotificationByType(@Param("type_notification") TypeNotification type_notification);
	
	
	
	
	
}
