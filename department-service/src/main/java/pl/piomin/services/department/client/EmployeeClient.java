package pl.piomin.services.department.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.piomin.services.department.model.Employee;

@FeignClient(name = "employee")
public interface EmployeeClient {

	@GetMapping("/department/{departmentId}")
	List<Employee> findByDepartment(@PathVariable("departmentId") String departmentId);
	
}
