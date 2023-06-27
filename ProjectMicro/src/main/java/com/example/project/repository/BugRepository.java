package com.example.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.BugEntity;



@Repository
public interface BugRepository extends JpaRepository<BugEntity, Integer>{
	List<BugEntity> findAllByProjectId(int projectid) ;
	List<BugEntity> findByStatus(String status);
	List<BugEntity> findByName(String name);
	
	Optional<BugEntity> findById(int id);
	
	@Modifying
    @Query("UPDATE BugEntity SET status = :status WHERE id = :id")
    int updateBugStatus(@Param("id") int id, @Param("status") String status);

//	@Query(value = "Select bugDetail from Bug bugDetail where status=:status")
//	Optional<BugEntity> findBugByStatusCustom(@Param("status") String status);

}
