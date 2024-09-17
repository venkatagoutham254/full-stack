package com.aforo.project.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aforo.project.model.Division;
@Repository
public interface DivisionRepository  extends JpaRepository<Division,Long>{

}
