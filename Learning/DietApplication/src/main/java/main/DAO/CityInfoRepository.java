package main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entity.CityInfo;

@Repository
public interface CityInfoRepository extends JpaRepository<CityInfo,Integer>{
	
	   @Query("SELECT c.city FROM CityInfo c WHERE c.cityName = :cityName")
	    Integer findCityIdByCityName(@Param("cityName") String cityName);
}
