package main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entity.GenderInfo;

@Repository
public interface GenderInfoRepository extends JpaRepository<GenderInfo,Integer>{
	
	@Query("SELECT g.genderId FROM GenderInfo g WHERE g.gender = :gender")
    Integer findGenderIdByGender(@Param("gender") String gender);

}
