package in.ajayit.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ajayit.entity.CitizenPlan;
import in.ajayit.request.SearchRequest;
import in.ajayit.service.ReportService;

@Controller
public class ReportController {  // used to handle request and response related to our application. When user click on btn the request go to controller and send the response.

	@Autowired
	private ReportService service;
	
	@GetMapping("/pdf") // when we click pdf it should download in the browser as attachment
	public void pdfExport(HttpServletResponse response) throws Exception { // to download pdf export
		response.setContentType("application/pdf");  // application/pdf - get file in pdf format
		response.addHeader("content-Disposition", "attachment;filename=plans.pdf;");  //pdf file will download in the browser
		service.exportPdf(response);	
	}
	
	@GetMapping("/excel") // when we click excel it should download in the browser as attachment
	public void excelExport(HttpServletResponse response) throws Exception { // to download excel export
		response.setContentType("application/octet-stream");  // application/octet-stream - get file in excel format
		response.addHeader("content-Disposition", "attachment;filename=plans.xls;");  //excel file will download in the browser
		service.exportExcel(response);	
	}
	
	@PostMapping("/searchData")
	public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model) { //this is will contain form data, @Model attribute that is used to reset our binding object into model scope
		List<CitizenPlan> plans= service.search(search); //calling search with search request(ReportService(I)). get the data form service
		model.addAttribute("plans", plans);//service data will come to controller, from the controller to the UI
		model.addAttribute("search", search);
		init(model);
	    return "index";
	
	}
	
	@GetMapping("/")   // url pattern as a slash means empty request
	public String indexPage(Model model) { //to display form  ... purpose of Model to send the data from controller to UI.
		//SearchRequest searchObj = new SearchRequest();// or below , way to use 
		model.addAttribute("search" , new SearchRequest());  // sending binding obj to UI, a new search obj is creating
		init(model);
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("names" , service.getPlanNames());
		model.addAttribute("status" , service.getPlanStatuses());
	}
	
}
