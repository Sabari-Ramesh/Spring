package main.service;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.Repository.DailyCalorieIntakeRepository;
import main.entity.DailyCalorieIntake;

@Service
public class ReportService {

    @Autowired
    private DailyCalorieIntakeRepository dailyCalorieIntakeRepository;

    public DailyCalorieIntake getDailyReport(Long userId, LocalDate date) {
        return dailyCalorieIntakeRepository.findByUserIdAndDate(userId, date);
    }

    public List<DailyCalorieIntake> getWeeklyReport(Long userId, LocalDate startDate, LocalDate endDate) {
        return dailyCalorieIntakeRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }
}
