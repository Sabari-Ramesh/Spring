package main.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.DTO.MealDetailsDTO;
import main.DTO.UsersDTO;
import main.Response.ResponseHandle;
import main.entity.MealDetails;
import main.entity.Users;
import main.service.MealDetailsService;

import main.Exceptions.*;

@RestController
@RequestMapping("/mealdetails")
public class MealDetailController {
	
	@Autowired
	private MealDetailsService mealDetailsService;

	// 2. Insert Meal Detail

	@PostMapping("/add")
	public ResponseEntity<?> insertMealDetail(@RequestBody MealDetailsDTO mealDetailDto) {

		// Convert DTO to Entity
		MealDetails mealDetail = new MealDetails();
		mealDetail.setMealType(mealDetailDto.getMealType());
		mealDetail.setMealDate(mealDetailDto.getMealDate());
		mealDetail.setQuantity(mealDetailDto.getQuantity());
		mealDetail.setCalories(mealDetailDto.getCalories());
		mealDetail.setProtein(mealDetailDto.getProtein());
		mealDetail.setCarboHydrate(mealDetailDto.getCarboHydrate());
		mealDetail.setVitamins(mealDetailDto.getVitamins());
		mealDetail.setFoodName(mealDetailDto.getFoodName());
		mealDetail.setUser(mealDetailDto.getUser());

		try {

			ResponseHandle response = mealDetailsService.insertMealDetail(mealDetail);
			MealDetails inserted = response.getMealDetail();
			mealDetailDto.setDateCreated(inserted.getDateCreated());
			mealDetailDto.setLastUpdate(inserted.getLastUpdate());
			mealDetailDto.setId(inserted.getId());
			return ResponseEntity.ok(mealDetailDto);

		} catch (DataIntegrityViolationException | UserNotFound e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (QuantityException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (FoodNameException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (MealTypeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// 3. Find By meal Id

	@GetMapping("/mealid=/{id}")
	public ResponseEntity<?> findByMealId(@PathVariable("id") long id) {

		MealDetailsDTO mealDetailDto = new MealDetailsDTO();
		MealDetails mealDetail = new MealDetails();
		mealDetail.setId(id);

		try {

			ResponseHandle response = mealDetailsService.findByMealId(mealDetail);
			MealDetails fetchedDetail = response.getMealDetail();
			return ResponseEntity.ok(mapToDto(fetchedDetail));

		} catch (MealIdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// 4. FindALL

	@GetMapping("/findall")
	public List<MealDetailsDTO> findAll() {

		ResponseHandle response = mealDetailsService.fetchAll();
		List<MealDetails> mealDetailList = response.getMealDetailsList();
		List<MealDetailsDTO> mealDetailsDto = new ArrayList<>();
		for (int i = 0; i < mealDetailList.size(); i++) {
			MealDetails mealDetail = mealDetailList.get(i);
			MealDetailsDTO getDetail = mapToDto(mealDetail);
			mealDetailsDto.add(getDetail);
		}

		return mealDetailsDto;
	}

	// 5. Find Meal Details By User ID(custom Querry )

	@GetMapping("/userid=/{userId}")
	public ResponseEntity<?> findMealDetailsByUserId(@PathVariable("userId") long userId) {

		try {

			ResponseHandle response = mealDetailsService.findMealDetailsByUserId(userId);
			List<MealDetails> list = response.getMealDetailsList();
			List<MealDetailsDTO> listDto = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				MealDetails mealDetail = list.get(i);
				MealDetailsDTO getDetail = mapToDto(mealDetail);
				listDto.add(getDetail);
			}

			if (!listDto.isEmpty()) {
				return ResponseEntity.ok(listDto);
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("No Details Available");
			}

		} catch (UserNotFound e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// 6. && 8. Named Querry with Aggregate

	@GetMapping("/namedwithaggregate")
	public ResponseEntity<?> getAvgCaloriesByDateRange(@RequestBody Map<String, String> dateRange) {

		LocalDate startDate = LocalDate.parse(dateRange.get("startDate"));
		LocalDate endDate = LocalDate.parse(dateRange.get("endDate"));
		try {
			
		ResponseHandle response = mealDetailsService.avgCaloriesByDateRange(startDate, endDate);
		double avgCalories = response.getCalories();
		return ResponseEntity.ok(avgCalories);
		}catch(DateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// 7. Custom With Projection

	@PostMapping("/associate-meal-details")
	public ResponseEntity<?> associateUserWithMealDetails(@RequestBody UsersDTO userDto) {

		Users user = new Users();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setMobileNumber(userDto.getMobileNumber());
		user.setGender(userDto.getGender());
		user.setCity(userDto.getCity());

		List<MealDetails> mealDetailsList = new ArrayList<>();
		for (MealDetailsDTO mealDetailDto : userDto.getMealDetails()) {
			MealDetails mealDetail = new MealDetails();
			mealDetail.setMealType(mealDetailDto.getMealType());
			mealDetail.setMealDate(mealDetailDto.getMealDate());
			mealDetail.setFoodName(mealDetailDto.getFoodName());
			mealDetail.setQuantity(mealDetailDto.getQuantity());
			mealDetail.setProtein(mealDetailDto.getProtein());
			mealDetail.setVitamins(mealDetailDto.getVitamins());
			mealDetail.setCalories(mealDetailDto.getCalories());
			mealDetail.setCarboHydrate(mealDetailDto.getCarboHydrate());
			mealDetail.setUser(user); // Set the user for the meal detail
			mealDetailsList.add(mealDetail);
		}

		user.setMealDetails(mealDetailsList);
		try {
			ResponseHandle response = mealDetailsService.associationUserWithMealDetails(user);
			return ResponseEntity.ok(response);
		} catch (InValidCityId e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (FoodNameException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (InValidEmailException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate Entry ");
		} catch (DateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (QuantityException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (MealTypeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// 9.Named With Projection

	@GetMapping("/groupedQuantityAndCalories")
	public ResponseEntity<?> getAvgCaloriesAndTotalQuantity(@RequestParam("calorieThreshold") double calorieThreshold) {

		try {

			ResponseHandle response = mealDetailsService.findAvgCaloriesAndTotalQuantity(calorieThreshold);
			List<MealSummary> summary = response.getMealSummary();
			if (!summary.isEmpty()) {
				return ResponseEntity.ok(summary);
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("No Details Available");
			}

		} catch (QuantityException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// Update Meal Detail

	@PutMapping("/update")
	public ResponseEntity<?> updateMealDetail(@RequestBody MealDetailsDTO mealDetailDto) {

		MealDetails mealDetail = new MealDetails();
		mealDetail.setId(mealDetailDto.getId());
		mealDetail.setFoodName(mealDetailDto.getFoodName());
		try {
			ResponseHandle response = mealDetailsService.updateMealDetail(mealDetail);
			MealDetails fetchedDetail = response.getMealDetail();
			return ResponseEntity.ok(mapToDto(fetchedDetail));
		} catch (MealIdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (FoodNameException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// Delete Meal Detail
	@DeleteMapping("/deletemealid=/{id}")
	public ResponseEntity<?> deleteMealDetail(@PathVariable("id") long id) {

		MealDetails mealDetail = new MealDetails();
		mealDetail.setId(id);

		try {
			ResponseHandle response = mealDetailsService.deleteId(mealDetail);
			return ResponseEntity.ok(response.getId() + " Sucessfully Deleted");
		} catch (MealIdNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	// Custom Queery with Projection

	@GetMapping("/customprojection")
	public List<MealDetailsProjection> findCustomMealDetails() {
		ResponseHandle response = mealDetailsService.findCustomMealDetails();

		List<MealDetailsProjection> mealDetail = response.getMealDetailProjection();
		return mealDetail;

	}

	//Convert DTO TO Entity
	
	public static MealDetailsDTO mapToDto(MealDetails fetchedDetail) {

		MealDetailsDTO mealDetailDto = new MealDetailsDTO();
		mealDetailDto.setId(fetchedDetail.getId());
		mealDetailDto.setCalories(fetchedDetail.getCalories());
		mealDetailDto.setCarboHydrate(fetchedDetail.getCarboHydrate());
		mealDetailDto.setDateCreated(fetchedDetail.getDateCreated());
		mealDetailDto.setFoodName(fetchedDetail.getFoodName());
		mealDetailDto.setLastUpdate(fetchedDetail.getLastUpdate());
		mealDetailDto.setMealDate(fetchedDetail.getMealDate());
		mealDetailDto.setMealType(fetchedDetail.getMealType());
		mealDetailDto.setProtein(fetchedDetail.getProtein());
		mealDetailDto.setQuantity(fetchedDetail.getQuantity());
		// mealDetailDto.setUser(fetchedDetail.getUser());
		mealDetailDto.setVitamins(fetchedDetail.getVitamins());

		return mealDetailDto;
	}

}
