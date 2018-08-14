package pl.piomin.services.department.resolver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import pl.piomin.services.department.client.EmployeeClient;
import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.repository.DepartmentRepository;

@Component
public class DepartmentQueries implements GraphQLQueryResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentQueries.class);
	
	@Autowired
	EmployeeClient employeeClient;
	@Autowired
	DepartmentRepository repository;
	
	public List<Department> departments() {
		LOGGER.info("Departments find");
		return repository.findAll();
	}
	
	public List<Department> departmentsByOrganization(Long organizationId) {
		LOGGER.info("Departments find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}

	public List<Department> departmentsByOrganizationWithEmployees(Long organizationId) {
		LOGGER.info("Departments find: organizationId={}", organizationId);
		List<Department> departments = repository.findByOrganization(organizationId);
		for (int i = 0; i < departments.size(); i++) {
			try {
				departments.get(i).setEmployees(employeeClient.findByDepartment(departments.get(i).getId()));
			} catch (InterruptedException e) {
				LOGGER.error("Error calling employee-service", e);
			}
		}
		return departments;
	}
	
	public Department department(Long id) {
		LOGGER.info("Department find: id={}", id);
		return repository.findById(id);
	}
	
}
