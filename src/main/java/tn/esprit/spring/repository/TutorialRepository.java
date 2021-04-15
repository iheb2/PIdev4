package tn.esprit.spring.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.models.Notification;
import tn.esprit.spring.models.TypeNotification;

public interface TutorialRepository extends JpaRepository<Notification, Long> {
  Page<Notification> findByMessage(String message , Pageable pageable);
  Page<Notification> findByMessageContaining(String message , Pageable pageable);
  
  List<Notification> findByMessageContaining(String message , Sort sort);
  
  
}
