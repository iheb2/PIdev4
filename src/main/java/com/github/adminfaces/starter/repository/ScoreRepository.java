package com.github.adminfaces.starter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.infra.model.*;
import com.github.adminfaces.starter.model.AmalWacelGhada;

@Repository
public interface ScoreRepository extends JpaRepository<AmalWacelGhada, Long> {


}
