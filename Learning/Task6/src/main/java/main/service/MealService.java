package main.service;


import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.Repository.MealDetailsRepository;
import main.entity.MealDetails;

@Service
public class MealService {

    @Autowired
    private MealDetailsRepository mealDetailsRepository;

    public MealDetails addMealDetails(MealDetails mealDetails) {
        return mealDetailsRepository.save(mealDetails);
    }

    public List<MealDetails> getMealsForDate(Long userId, LocalDate date) {
        return mealDetailsRepository.findByUserIdAndMealDate(userId, date);
    }
}

