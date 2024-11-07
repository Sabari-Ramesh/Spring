package dietTrackerApplication.DAO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component

public interface MealDetailsProjection {
	String getFoodName();
	double getQuantity();
	long getId();
	LocalDate getDate();
}
