package tn.esprit.spring.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
