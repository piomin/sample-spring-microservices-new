package pl.piomin.services.organization.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.piomin.services.organization.model.Employee;

@FeignClient(name = "employee", url = "${microservices.employee.url}")
public interface EmployeeClient {

	@GetMapping("/employee/organization/{organizationId}")
	List<Employee> findByOrganization(@PathVariable("organizationId") String organizationId);
	
}
