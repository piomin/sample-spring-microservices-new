package pl.piomin.services.department.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.apollographql.apollo.ApolloCall.Callback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import pl.piomin.services.department.model.Employee;

public class EmployeeClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeClient.class);
	private static final int TIMEOUT = 5000;
	private static final String SERVICE_NAME = "EMPLOYEE-SERVICE"; 
	private static final String SERVER_URL = "http://localhost:%d/graphql";
	
	Random r = new Random();
	
	@Autowired
	private EurekaClient discoveryClient;
	
	public List<Employee> findByDepartment(Long departmentId) throws InterruptedException {
		List<Employee> employees = new ArrayList<>();
		Application app = discoveryClient.getApplication(SERVICE_NAME);
		InstanceInfo ii = app.getInstances().get(r.nextInt(app.size()));
		ApolloClient client = ApolloClient.builder().serverUrl(String.format(SERVER_URL, ii.getPort())).build();
		CountDownLatch lock = new CountDownLatch(1);
		client.query(EmployeesQuery.builder().build()).enqueue(new Callback<EmployeesQuery.Data>() {

			@Override
			public void onFailure(ApolloException ex) {
				LOGGER.info("Err: {}", ex);
				lock.countDown();
			}

			@Override
			public void onResponse(Response<EmployeesQuery.Data> res) {
				LOGGER.info("Res: {}", res);
				employees.addAll(res.data().employees().stream().map(emp -> new Employee(emp.name(), emp.age(), null)).collect(Collectors.toList()));
				lock.countDown();
			}

		});
		lock.await(TIMEOUT, TimeUnit.MILLISECONDS);
		return employees;
	}
	
}
