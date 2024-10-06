package main.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.DTO.MealDetailDTO;
import main.entity.MealDetails;
import main.entity.MealInfo;
import main.entity.User;
import main.service.MealDetailService;

@RestController
@RequestMapping("/mealdetails")
public class MealDetailController {

	@Autowired
	private MealDetailService mealDetailService;

	//2.Insert

	@PostMapping("/insert")
	private void insertMealDetail(@RequestBody MealDetailDTO mealDetailDto) {
		MealDetails mealDetail=mapToEntity(mealDetailDto);
		 mealDetailService.insertDetails(mealDetail);
	}
	
	//3.Find By Id
	
	@GetMapping("/fetchByid/{id}")
	public MealDetailDTO findByMealId(@PathVariable("id") long id) {
		MealDetails detail=mealDetailService.fetchById(id);
		
		MealDetailDTO mealDetailDto=maptoDto(detail);
		
		return mealDetailDto;
	}

	//4.Find All
	
	@GetMapping("/findAll")
	public List<MealDetailDTO> findAll(){
		
		List<MealDetails> mealDetail=mealDetailService.fetchAll();
		List<MealDetailDTO> mealDto=new ArrayList<>();
		
		for(int i=0;i<mealDetail.size();i++) {
			MealDetails detail=mealDetail.get(i);
			MealDetailDTO mealDetailDto=maptoDto(detail);
			mealDto.add(mealDetailDto);
		}
		
		return mealDto;
	}
	
	
	//5.Find By User Id ( Custom Query )
	
	@GetMapping("/findbyuserId/{userId}")
	public List<MealDetailDTO> findMealDetailsByUserId(@PathVariable("userId") long userId){
		List<MealDetails> mealDetail=mealDetailService.fetchByUserId(userId);
		List<MealDetailDTO> mealDto=new ArrayList<>();
		
		for(int i=0;i<mealDetail.size();i++) {
			MealDetails detail=mealDetail.get(i);
			MealDetailDTO mealDetailDto=maptoDto(detail);
			mealDto.add(mealDetailDto);
		}
		
		return mealDto;
	}
	
	//6. Find By Quantity Range (Named Query)
	
	  @GetMapping("quantityRange")
	    public List<MealDetailDTO> getMealsByQuantityRange(@RequestParam double min, @RequestParam double max) {
		  List<MealDetails> mealDetail=mealDetailService.findByQuantityRange(min, max);
		  List<MealDetailDTO> mealDto=new ArrayList<>();
			
			for(int i=0;i<mealDetail.size();i++) {
				MealDetails detail=mealDetail.get(i);
				MealDetailDTO mealDetailDto=maptoDto(detail);
				mealDto.add(mealDetailDto);
			}
			
			return mealDto;
	    }
	  
	  
	  //7.Custom Query with Projection
	  
	  @GetMapping("/customprojection")
		public List<MealDetailsProjection> findCustomMealDetails() {
			
		  List<MealDetailsProjection> mealDetailProjection =mealDetailService.findCustomMealDetails();

			return mealDetailProjection;

		}
	  
	  //8.Custom with Aggregate
		@GetMapping("/aggregate")
		public double getAvgCaloriesByDateRange(@RequestBody Map<String, String> dateRange) {

			LocalDate startDate = LocalDate.parse(dateRange.get("startDate"));
			LocalDate endDate = LocalDate.parse(dateRange.get("endDate"));
			double avgCalorie=mealDetailService.findAvgCaloriesByDateRange(startDate, endDate);
			return avgCalorie;
		}
		
		//9.Named with clauses
		
		@GetMapping("/groupedQuantityAndCalories")
		public List<MealSummary> getAvgCaloriesAndTotalQuantity(@RequestParam("calorieThreshold") double calorieThreshold) {
			
				List<MealSummary> list = mealDetailService.avgCaloriesAndTotalQuantity(calorieThreshold);
				return list;
		}
	
	//Map to Dto
	
	private MealDetailDTO maptoDto(MealDetails detail) {
	
		MealDetailDTO detailDto=new MealDetailDTO();
		
		detailDto.setMealId(detail.getMealId());
		detailDto.setFoodName(detail.getFoodName());
		detailDto.setCalories(detail.getCalories());
		detailDto.setCarbs(detail.getCarbs());
		detailDto.setProtein(detail.getProtein());
		detailDto.setVitamins(detail.getVitamins());
		detailDto.setQuantity(detail.getQuantity());
		detailDto.setMealDate(detail.getMealDate());
		detailDto.setDateCreated(detail.getDateCreated());
		detailDto.setLastUpdate(detail.getLastUpdate());
		
		User user=detail.getUser();
		
		detailDto.setUserid(user.getUserId());
		
		MealInfo mealInfo=detail.getMealType();
		
		detailDto.setMealTypeid(mealInfo.getMealType());
		

		
		
		return detailDto;
	}
	
	//Map To Entity

	private MealDetails mapToEntity(MealDetailDTO mealDetailDto) {
		
		//MealDetail
		
		MealDetails detail=new MealDetails();
		
		detail.setCalories(mealDetailDto.getCalories());
		detail.setCarbs(mealDetailDto.getCarbs());
		detail.setFoodName(mealDetailDto.getFoodName());
		detail.setMealDate(mealDetailDto.getMealDate());
		detail.setProtein(mealDetailDto.getProtein());
		detail.setQuantity(mealDetailDto.getQuantity());
		detail.setVitamins(mealDetailDto.getVitamins());
		
		//Mealinfo
		
		MealInfo mealType=new MealInfo();
		mealType.setMealType(mealDetailDto.getMealTypeid());
		
		
		//user
		
		User user=new User();		
		user.setUserId(mealDetailDto.getUserid());
		
		//Set Foreign Key
		
		detail.setMealType(mealType);		
		detail.setUser(user);
		
		
		return detail;
		
	}
	
}
