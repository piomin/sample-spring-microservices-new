package pl.piomin.services.employee.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import pl.piomin.services.employee.model.Employee;
import pl.piomin.services.employee.repository.EmployeeRepository;

public class EmployeeMutations implements GraphQLMutationResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeQueries.class);
	
	@Autowired
	EmployeeRepository repository;
	
	public Employee newEmployee(Employee employee) {
		LOGGER.info("Employee add: employee={}", employee);
		return repository.add(employee);
	}
	
}
