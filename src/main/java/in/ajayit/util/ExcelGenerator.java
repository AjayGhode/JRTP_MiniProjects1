package in.ajayit.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ajayit.entity.CitizenPlan;
import in.ajayit.repo.CitizenPlanRepository;

@Component
public class ExcelGenerator {
	

	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {
		// Workbook workbook = new XSSFWorkbook(); //below line and this line are same
		// just in extension difference. so, in this file with .xlsx extension
		Workbook workbook = new HSSFWorkbook(); // excel file will created. file extendtion xsl
		Sheet sheet = workbook.createSheet("plans-data"); // sheet will create
		Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");

		int dataRowIndex = 1;

		for (CitizenPlan plan : records) { // to get each records and created one row records
			Row dataRow = sheet.createRow(dataRowIndex); // data row will be created
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());

			if (null != plan.getPlanStartDate()) {

				dataRow.createCell(4).setCellValue(plan.getPlanStartDate() + "");

			} else {
				dataRow.createCell(4).setCellValue("N/A");
			}

			if (null != plan.getPlanEndDate()) {

				dataRow.createCell(5).setCellValue(plan.getPlanEndDate() + "");

			} else {

				dataRow.createCell(5).setCellValue("N/A");
			}

			if (null != plan.getBenefitAmt()) {

				dataRow.createCell(6).setCellValue(plan.getBenefitAmt());

			} else {

				dataRow.createCell(6).setCellValue("N/A");

			}

			dataRowIndex++; // will increment data row
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.close();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

	}

}



//FileOutputStream - will create file in current/local folder
//ServletOutputStream - will send to the browser