package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.BO.MealDetailsBO;
import main.entity.MealDetails;

@Service
public class MealDetailService {
	
	@Autowired
	MealDetailsBO mealDetailBo;

	//Insert
	
	public void insertDetails(MealDetails mealDetail) {
		mealDetailBo.insertDetails(mealDetail);
	}

	//Find By Id
	
	public MealDetails fetchById(long id) {
		MealDetails detail=mealDetailBo.fetchById(id);
		return detail;
	}

	//Find All
	
	public List<MealDetails> fetchAll() {
		List<MealDetails> list=mealDetailBo.fetchAll();
		return list;
	}
}
