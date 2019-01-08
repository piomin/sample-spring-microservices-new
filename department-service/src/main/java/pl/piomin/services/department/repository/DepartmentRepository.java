package pl.piomin.services.department.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.piomin.services.department.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {

	List<Department> findByOrganizationId(String organizationId);
	
}
