package pl.piomin.services.department.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apollographql.apollo.ApolloCall.Callback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import pl.piomin.services.department.client.EmployeeClient;
import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.repository.DepartmentRepository;

@RestController
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	Random r = new Random();
	
	@Autowired
	private EurekaClient discoveryClient;
	@Autowired
	DepartmentRepository repository;
	@Autowired
	EmployeeClient employeeClient;
	
	@PostMapping("/")
	public Department add(@RequestBody Department department) {
		LOGGER.info("Department add: {}", department);
		return repository.add(department);
	}
	
	@GetMapping("/{id}")
	public Department findById(@PathVariable("id") Long id) {
		LOGGER.info("Department find: id={}", id);
		return repository.findById(id);
	}
	
	@GetMapping("/")
	public List<Department> findAll() {
		LOGGER.info("Department find");
		testApollo();
		return repository.findAll();
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}
	
	@GetMapping("/organization/{organizationId}/with-employees")
	public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		List<Department> departments = repository.findByOrganization(organizationId);
		departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
		return departments;
	}
	
	public void testApollo() {
		Application app = discoveryClient.getApplication("EMPLOYEE-SERVICE");
		InstanceInfo ii = app.getInstances().get(r.nextInt(app.size()));
		ApolloClient client = ApolloClient.builder().serverUrl("http://" + ii.getIPAddr() + ":" + ii.getPort() + "/graphql").build();
		client.query(pl.piomin.services.department.client.EmployeesQuery.builder().build()).enqueue(new Callback<pl.piomin.services.department.client.EmployeesQuery.Data>() {

			@Override
			public void onFailure(ApolloException arg0) {
				LOGGER.info("Err: {}", arg0);
			}

			@Override
			public void onResponse(Response<pl.piomin.services.department.client.EmployeesQuery.Data> arg0) {
				LOGGER.info("Res: {}", arg0);
			}


		});

	}
}
