package dietTrackerApplication.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dietTrackerApplication.DTO.MealDetailDTO;
import dietTrackerApplication.entity.MealDetails;
import dietTrackerApplication.entity.MealInfo;
import dietTrackerApplication.entity.User;
import dietTrackerApplication.service.ExportService;
import dietTrackerApplication.service.MealDetailService;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private MealDetailService mealDetailService;

    @Autowired
    private ExportService exportService;

    @GetMapping("/meal-details")
    public ResponseEntity<?> exportMealDetails(@RequestParam String format) throws IOException {
        List<MealDetails> result = mealDetailService.fetchAll().getMealDetailsList();
        List<MealDetailDTO> mealDetails=new ArrayList();
        for(MealDetails details:result) {
        	MealDetailDTO mealdetail= maptoDto(details);
        	mealDetails.add(mealdetail);
        }
       
        ByteArrayInputStream fileInputStream;
        String fileName;
        
        switch (format.toLowerCase()) {
            case "csv":
                fileInputStream = exportService.exportToCsv(mealDetails);
                fileName = "meal-details.csv";
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                        .contentType(MediaType.parseMediaType("text/csv"))
                        .body(new InputStreamResource(fileInputStream));
                
            case "excel":
                fileInputStream = exportService.exportToExcel(mealDetails);
                fileName = "meal-details.xlsx";
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .body(new InputStreamResource(fileInputStream));

            case "pdf":
                fileInputStream = exportService.exportToPdf(mealDetails);
                fileName = "meal-details.pdf";
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                        .contentType(MediaType.parseMediaType("application/pdf"))
                        .body(new InputStreamResource(fileInputStream)); 

            default:
                return ResponseEntity.badRequest().body("Invalid format. Supported formats: CSV, Excel, PDF");
        }
    }
    
    
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
    
}
