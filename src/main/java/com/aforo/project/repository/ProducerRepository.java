package com.aforo.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.aforo.project.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    // Custom query to perform a case-insensitive search by name
    @Query("SELECT p FROM Producer p WHERE LOWER(p.name) = LOWER(:name)")
    Optional<Producer> findByNameIgnoreCase(@Param("name") String name);

}
