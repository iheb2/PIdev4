package tn.esprit.spring.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.model.Info;




public interface InfopagRepository extends JpaRepository<Info, Long> {
 
  Page<Info> findByQuestionContaining(String Question , Pageable pageable);
  

  
  
}