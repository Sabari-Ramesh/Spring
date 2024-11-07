package dietTrackerApplication.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dietTrackerApplication.DTO.MealDetailDTO;


@Service
public class ExportService {

    // CSV Export
    public ByteArrayInputStream exportToCsv(List<MealDetailDTO> mealDetails) {
        // Generate CSV content
        StringBuilder csvContent = new StringBuilder("Meal ID, Meal, Date, UserID, Food Name, Quantity, Calories, Protein, Carbs, Vitamins, Date Created, Last Updated\n");
        for (MealDetailDTO detail : mealDetails) {
            csvContent.append(detail.getMealId()).append(", ")
                      .append(detail.getMeal()).append(", ")
                      .append(detail.getMealDate()).append(", ")
                      .append(detail.getUserid()).append(", ")
                      .append(detail.getFoodName()).append(", ")
                      .append(detail.getQuantity()).append(", ")
                      .append(detail.getCalories()).append(", ")
                      .append(detail.getProtein()).append(", ")
                      .append(detail.getCarbs()).append(", ")
                      .append(detail.getVitamins()).append(", ")
                      .append(detail.getDateCreated()).append(", ")
                      .append(detail.getLastUpdate()).append("\n");
        }
        return new ByteArrayInputStream(csvContent.toString().getBytes());
    }

    // Excel Export
    public ByteArrayInputStream exportToExcel(List<MealDetailDTO> mealDetails) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Meal Details");
            // Header
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Meal ID", "Meal", "Date", "UserID", "Food Name", "Quantity", "Calories", "Protein", "Carbs", "Vitamins", "Date Created", "Last Updated"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            // Data rows
            int rowIdx = 1;
            for (MealDetailDTO detail : mealDetails) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(detail.getMealId());
                row.createCell(1).setCellValue(detail.getMeal());
                row.createCell(2).setCellValue(detail.getMealDate().toString());
                row.createCell(3).setCellValue(detail.getUserid());
                row.createCell(4).setCellValue(detail.getFoodName());
                row.createCell(5).setCellValue(detail.getQuantity());
                row.createCell(6).setCellValue(detail.getCalories());
                row.createCell(7).setCellValue(detail.getProtein());
                row.createCell(8).setCellValue(detail.getCarbs());
                row.createCell(9).setCellValue(detail.getVitamins());
                row.createCell(10).setCellValue(detail.getDateCreated().toString());
                row.createCell(11).setCellValue(detail.getLastUpdate().toString());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
    
    public ByteArrayInputStream exportToPdf(List<MealDetailDTO> mealDetails) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Meal Details Report"));
            for (MealDetailDTO detail : mealDetails) {
                document.add(new Paragraph(detail.toString()));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
   
}
