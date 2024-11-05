package dietTrackerApplication.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dietTrackerApplication.DAO.MealDetailsProjection;
import dietTrackerApplication.DAO.MealSummary;
import dietTrackerApplication.DTO.MealDetailDTO;
import dietTrackerApplication.Exception.DateException;
import dietTrackerApplication.Exception.InvalidNumberException;
import dietTrackerApplication.Exception.MealIdNotFoundException;
import dietTrackerApplication.Exception.MealTypeException;
import dietTrackerApplication.Exception.NameException;
import dietTrackerApplication.Exception.QuantityException;
import dietTrackerApplication.Exception.UserNotFound;
import dietTrackerApplication.entity.MealDetails;
import dietTrackerApplication.entity.MealInfo;
import dietTrackerApplication.entity.User;
import dietTrackerApplication.response.Response;
import dietTrackerApplication.service.MealDetailService;

@RestController
@RequestMapping("/mealdetails")
public class MealDetailController {

	@Autowired
	private MealDetailService mealDetailService;

	@Autowired
	private Response response;

	Logger log = Logger.getLogger(MealDetailController.class);

	// 2.Insert

	@PostMapping("/insert")
	private ResponseEntity<?> insertMealDetail(@RequestBody MealDetailDTO mealDetailDto) {
		log.info("Insert Method is triggred...");
		try {

			MealDetails mealDetail = mapToEntity(mealDetailDto);
			response = mealDetailService.insertDetails(mealDetail);
			if (response.getSucessMsg() != null) {
				String str = "Meal Details are Sucessfully Created and Your Generated Meal Id : " + response.getId();
				log.info(str);
				return ResponseEntity.ok("Meal Details are Sucessfully Created and Your Generated Meal Id : " + response.getId());
			} else {
				log.info("Meal Details are not Sucessfully Created");
				return ResponseEntity.ok("Meal Details are not Sucessfully Created");
			}
		} catch (DataIntegrityViolationException | UserNotFound e) {
			log.error("Duplicate Entry ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateException e) {
			log.error("Invalid Date ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (QuantityException e) {
			log.error("Invalid Quantity ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (NameException e) {
			log.error("Invalid Name Entry ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (MealTypeException e) {
			log.error("Invalid MealType  ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// 3.Find By Id

	@GetMapping("/fetchByid/{id}")
	public ResponseEntity<?> findByMealId(@PathVariable("id") long id) {

		try {
			log.info("Find By id method is triggred...");
			response = mealDetailService.fetchById(id);
			MealDetails detail = response.getMealDetail();
			MealDetailDTO mealDetailDto = maptoDto(detail);
			log.info(mealDetailDto);
			return ResponseEntity.ok(mealDetailDto);
		} catch (MealIdNotFoundException e) {
			log.error("Meal Id Not Found ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// 4.Find All

	@GetMapping("/findAll")
	public List<MealDetailDTO> findAll() {

		log.info("Find All method is triggred....");
		response = mealDetailService.fetchAll();
		List<MealDetails> mealDetailList = response.getMealDetailsList();
		List<MealDetailDTO> mealDetailsDto = new ArrayList<>();
		for (int i = 0; i < mealDetailList.size(); i++) {
			MealDetails mealDetail = mealDetailList.get(i);
			MealDetailDTO getDetail = maptoDto(mealDetail);
			mealDetailsDto.add(getDetail);
		}
		String sucess=response.getSucessMsg();
		log.info(sucess);
		log.info(mealDetailsDto);
		return mealDetailsDto;
	}

	// 5.Find By User Id ( Custom Query )

	@GetMapping("/findbyuserId/{userId}")
	public ResponseEntity<?> findMealDetailsByUserId(@PathVariable("userId") long userId) {
		try {
			log.info("Find By user Id Method is triggred...");
			response = mealDetailService.fetchByUserId(userId);
			List<MealDetails> list = response.getMealDetailsList();
			List<MealDetailDTO> listDto = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				MealDetails mealDetail = list.get(i);
				MealDetailDTO getDetail = maptoDto(mealDetail);
				listDto.add(getDetail);
			}
			if (!listDto.isEmpty()) {
				String sucess=response.getSucessMsg();
				log.info(sucess);
				log.info(listDto);
				return ResponseEntity.ok(listDto);
			} else {
				String sucess=response.getSucessMsg();
				log.info(sucess);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("No Details Available");
			}

		} catch (UserNotFound e) {
			log.error("User Not Found ERROR", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// 6. Find By Quantity Range (Named Query)

	@GetMapping("quantityRange")
	public ResponseEntity<?> getMealsByQuantityRange(@RequestParam double min, @RequestParam double max) {

		log.info("Find By Quantity method is triggred...");
		try {
			response = mealDetailService.findByQuantityRange(min, max);
			List<MealDetails> mealDetail = response.getMealDetailsList();
			if (mealDetail.isEmpty()) {
				String sucess=response.getSucessMsg();
				log.info(sucess);
				return ResponseEntity.ok(response.getSucessMsg());
			}

			List<MealDetailDTO> mealDto = new ArrayList<>();

			for (int i = 0; i < mealDetail.size(); i++) {
				MealDetails detail = mealDetail.get(i);
				MealDetailDTO mealDetailDto = maptoDto(detail);
				mealDto.add(mealDetailDto);
			}
			String sucess=response.getSucessMsg();
			log.info(sucess);
			log.info(mealDto);
			return ResponseEntity.ok(mealDto);
		} catch (InvalidNumberException e) {
			log.error("Invalid Number ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// 7.Custom Query with Projection

	@GetMapping("/customprojection")
	public List<MealDetailsProjection> findCustomMealDetails() {
		log.info("Custom Query With Project method is triggred...");
		response = mealDetailService.findCustomMealDetails();
		List<MealDetailsProjection> mealDetailProjection = response.getMealDetailProjection();
		String sucess=response.getSucessMsg();
		log.info(sucess);
		log.info(mealDetailProjection);
		return mealDetailProjection;

	}

	// 8.Custom with Aggregate

	@GetMapping("/aggregate")
	public ResponseEntity<?> getAvgCaloriesByDateRange(@RequestBody Map<String, String> dateRange) {

		log.info("Custom with Aggregate Method is triggred..");
		LocalDate startDate = LocalDate.parse(dateRange.get("startDate"));
		LocalDate endDate = LocalDate.parse(dateRange.get("endDate"));
		try {

			response = mealDetailService.findAvgCaloriesByDateRange(startDate, endDate);
			double avgCalories = response.getAvg();
			String str = "Average Calories " + avgCalories;
			String sucess=response.getSucessMsg();
			log.info(sucess);
			log.info(str);
			return ResponseEntity.ok("Average Calories " + avgCalories);
		} catch (DateException e) {
			log.error("Invalid Date ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// 9.Named with clauses

	@GetMapping("/groupedQuantityAndCalories")
	public ResponseEntity<?> getAvgCaloriesAndTotalQuantity(@RequestParam("calorieThreshold") double calorieThreshold) {

		log.info("Named with Clauses method is triggred...");
		try {

			response = mealDetailService.avgCaloriesAndTotalQuantity(calorieThreshold);
			List<MealSummary> summary = response.getMealSummary();
			if (summary != null && !summary.isEmpty()) {
				String sucess=response.getSucessMsg();
				log.info(sucess);
				log.info(summary);
				return ResponseEntity.ok(summary);
			} else {
				String sucess=response.getSucessMsg();
				log.info(sucess);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("No Details Available");
			}

		} catch (QuantityException e) {
			log.error("Invalid Quantity ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	//Update
	
	@PutMapping("/update")
	public ResponseEntity<?> updateMealDetail(@RequestBody MealDetailDTO mealDetailDto) {
	    
	    log.info("Update Method is triggered...");

	    // Convert DTO to entity
	    MealDetails mealDetail = new MealDetails();
	    mealDetail.setMealId(mealDetailDto.getMealId());
	    mealDetail.setFoodName(mealDetailDto.getFoodName());
	    
	   log.info("Update Method is triggred...");

	    try {
	        response = mealDetailService.updateMealDetail(mealDetail); 
	        String str = "Meal Details are Sucessfully Updated for Meal Id : " + response.getId();
	        String sucess=response.getSucessMsg();
			log.info(sucess);
	        return ResponseEntity.ok(str); 
	        
	    } catch (MealIdNotFoundException e) {
	        log.error("Meal ID Not Found Error: ", e);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        
	    } catch (NameException e) {
	        log.error("Invalid Food Name Error: ", e);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

	// Map to Dto

	private MealDetailDTO maptoDto(MealDetails detail) {

		MealDetailDTO detailDto = new MealDetailDTO();

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

		User user = detail.getUser();
		detailDto.setUserid(user.getUserId());

		MealInfo mealInfo = detail.getMealType();
		detailDto.setMeal(mealInfo.getMeal());

		return detailDto;
	}

	// Map To Entity

	private MealDetails mapToEntity(MealDetailDTO mealDetailDto) {

		// MealDetail

		MealDetails detail = new MealDetails();

		detail.setCalories(mealDetailDto.getCalories());
		detail.setCarbs(mealDetailDto.getCarbs());
		detail.setFoodName(mealDetailDto.getFoodName());
		detail.setMealDate(mealDetailDto.getMealDate());
		detail.setProtein(mealDetailDto.getProtein());
		detail.setQuantity(mealDetailDto.getQuantity());
		detail.setVitamins(mealDetailDto.getVitamins());
		
		//Setting Obj Foreign Key

		// Mealinfo

		MealInfo mealType = new MealInfo();
		mealType.setMeal(mealDetailDto.getMeal());
		
		// user

		User user = new User();
		user.setUserId(mealDetailDto.getUserid());

		// Set Foreign Key

		detail.setMealType(mealType);
		detail.setUser(user);

		return detail;

	}

}
