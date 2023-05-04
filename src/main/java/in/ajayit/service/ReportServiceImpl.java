package in.ajayit.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ajayit.entity.CitizenPlan;
import in.ajayit.repo.CitizenPlanRepository;
import in.ajayit.request.SearchRequest;
import in.ajayit.util.EmailUtils;
import in.ajayit.util.ExcelGenerator;
import in.ajayit.util.PdfGenerator;

@Service // represent as spring bean
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator; 
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanNames() {  // this method will give unique plan available in the table
		
		return planRepo.getPlanNames();  //service method will call the repository method & return in list
	}

	@Override
	public List<String> getPlanStatuses() {
		
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		// copy the data from binding obj to entity obj
		CitizenPlan entity = new CitizenPlan();
		System.out.println("Request=="+request);
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
	
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
	
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null!=request.getStartDate() && !"".equals(request.getStartDate())) {
			  String startDate = request.getStartDate();
			  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(startDate, formatter);
			  entity.setPlanStartDate(localDate);
					}
		
		if(null!=request.getEndDate() && !"".equals(request.getEndDate())) {
			  String endDate = request.getEndDate();
			  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			  //convert String to LocalDate
			  LocalDate localDate = LocalDate.parse(endDate, formatter);
			  entity.setPlanEndDate(localDate);
					}
	//Example.of(entity) for it generate the query dynamically based condition
		return planRepo.findAll(Example.of(entity)); //findAll- to retrieve all the records available in the table & will display
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans = planRepo.findAll(); //get the data and store in collection obj
		excelGenerator.generate(response, plans, f);  //calling util class. With that data calling helper class method. response - used to send file to the browser. plans- this data which write in the excel. f- create a file in the system. here following 'Single responsibility Principle' means one class should perform one action.
		
		//Logic to send email
		String subject = "Test mail subject";
		String body = "<h1> Test mail body </h1>";
		String to = "veer.ajayg@gmail.com";
		
		emailUtils.sendEmail(subject, body, to, f);
		
		f.delete();
		
		return true;
	}

	
	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		File f = new File("Plans.pdf");
		List<CitizenPlan> plans =  planRepo.findAll();
		
		pdfGenerator.generate(response, plans, f);
		//Logic to send email
		String subject = "Test mail subject";
		String body = "<h1> Test mail body </h1>";
		String to = "veer.ajayg@gmail.com";
				
		emailUtils.sendEmail(subject, body, to, f);
				
		f.delete();
				
		return true;
	}

}
