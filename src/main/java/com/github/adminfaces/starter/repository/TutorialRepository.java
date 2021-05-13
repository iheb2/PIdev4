package com.github.adminfaces.starter.repository;


import java.util.Date;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.adminfaces.starter.infra.model.*;
import com.github.adminfaces.starter.model.Notification;


public interface TutorialRepository extends JpaRepository<Notification, Long> {
  Page<Notification> findByMessage(String message , Pageable pageable);
  Page<Notification> findByMessageContaining(String message , Pageable pageable);
  
  List<Notification> findByMessageContaining(String message , Sort sort);
  
  
}
