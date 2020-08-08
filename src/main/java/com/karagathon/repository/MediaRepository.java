package com.karagathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.karagathon.model.Media;
import com.karagathon.model.Violation;
import com.karagathon.model.Violator;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
	
	@Query("SELECT v FROM Media v WHERE v.violation = :violation")    
    List<Media> findByViolation(@Param("violation") Violation violation);
	
	@Query("SELECT v FROM Media v WHERE v.violator = :violator")    
    List<Media> findByViolator(@Param("violator") Violator violator);
}
