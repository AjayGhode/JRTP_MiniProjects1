package in.ajayit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.ajayit.entity.CitizenPlan;
import in.ajayit.request.SearchRequest;

public interface ReportService { //  this interface to have business logic

	public List<String> getPlanNames();//get the data from the table to display in the drop down
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception ; //send report to email
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
	
}
