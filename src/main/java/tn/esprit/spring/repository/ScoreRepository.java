package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.models.AmalWacelGhada;
import tn.esprit.spring.models.User;

@Repository
public interface ScoreRepository extends JpaRepository<AmalWacelGhada, Long> {


}
