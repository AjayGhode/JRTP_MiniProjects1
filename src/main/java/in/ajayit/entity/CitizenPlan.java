package in.ajayit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIZEN_PLANS_INFO")
public class CitizenPlan {  // this class is mapped with DB table
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer citizenId;//this entity representing the table name in the DB
	private String citizenName;
	private String gender;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitAmt;
	private String denialReason;
	private LocalDate terminatedDate;
	private String terminationRsn;

}
