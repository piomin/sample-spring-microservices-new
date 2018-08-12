package pl.piomin.services.employee.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import pl.piomin.services.employee.model.Employee;
import pl.piomin.services.employee.repository.EmployeeRepository;

@Component
public class EmployeeMutations implements GraphQLMutationResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeQueries.class);
	
	@Autowired
	EmployeeRepository repository;
	
	public Employee newEmployee(Employee employee) {
		LOGGER.info("Employee add: employee={}", employee);
		return repository.add(employee);
	}
	
	public boolean deleteEmployee(Long id) {
		return true;
	}
	
	public Employee updateEmployee(Long id, Employee employee) {
		LOGGER.info("Employee add: id={}, employee={}", id, employee);
		return repository.add(employee);
	}
	
}
