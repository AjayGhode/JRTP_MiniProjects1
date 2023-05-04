package in.ajayit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ajayit.entity.CitizenPlan;

public interface CitizenPlanRepository 
				extends JpaRepository<CitizenPlan, Integer> {  // to perform operation with table we are using our repo

	@Query("select distinct (planName) from CitizenPlan")  // it is HQL query. It is use to get the data to display in the drop down.
	public List<String> getPlanNames();
	
	@Query("select distinct (planStatus) from CitizenPlan")//these are the custom queries, planStatus as var name
	public List<String> getPlanStatus();
}
