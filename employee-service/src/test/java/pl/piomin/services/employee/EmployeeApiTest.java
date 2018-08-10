package pl.piomin.services.employee;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apollographql.apollo.ApolloCall.Callback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import pl.piomin.services.employee.model.EmployeesQuery;
import pl.piomin.services.employee.model.EmployeesQuery.Data;

public class EmployeeApiTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeApiTest.class);
	
	private CountDownLatch lock = new CountDownLatch(1);
	
	@Test
	public void testClient() throws InterruptedException {
		ApolloClient client = ApolloClient.builder().serverUrl("http://localhost:8080/graphql").build();
		client.query(EmployeesQuery.builder().build()).enqueue(new Callback<EmployeesQuery.Data>() {

			@Override
			public void onFailure(ApolloException arg0) {
				LOGGER.error("Error", arg0);
				lock.countDown();
			}

			@Override
			public void onResponse(Response<Data> res) {
				LOGGER.info("Res: {}", res.data().employees());
				lock.countDown();
			}
		});
		lock.await(10000, TimeUnit.MILLISECONDS);
	}
	
}
