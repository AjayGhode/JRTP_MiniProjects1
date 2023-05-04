package in.ajayit.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {  //form binding - when user select the values and click on search btn, should be able to capture the date in the form of obj.

	//Which data coming from the UI to represent that data in the obj
	private String planName;
	private String planStatus;
	private String gender;
	private String startDate;  //yyyy-MM-dd
	private String endDate;
	
}
