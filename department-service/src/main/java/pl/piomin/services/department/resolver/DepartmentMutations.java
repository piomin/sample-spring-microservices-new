package pl.piomin.services.department.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.repository.DepartmentRepository;

@Component
public class DepartmentMutations implements GraphQLMutationResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentQueries.class);
	
	@Autowired
	DepartmentRepository repository;
	
	public Department newDepartment(Department department) {
		LOGGER.info("Department add: department={}", department);
		return repository.add(department);
	}
	
	public boolean deleteDepartment(Long id) {
		LOGGER.info("Department delete: id={}", id);
		return repository.delete(id);
	}
	
	public Department updateDepartment(Long id, Department department) {
		LOGGER.info("Department update: id={}, department={}", id, department);
		return repository.update(id, department);
	}
	
}
