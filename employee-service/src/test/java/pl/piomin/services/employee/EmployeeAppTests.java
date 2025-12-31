package pl.piomin.services.employee;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;
import pl.piomin.services.employee.model.Employee;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.config.discovery.enabled=false"
    }
)
@AutoConfigureRestTestClient
public class EmployeeAppTests {

    @Autowired
    RestTestClient restClient;

    @Test
    void findAll() {
        restClient.get()
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Employee[].class)
                .value(employees -> assertTrue(employees.length > 0));
    }

    @Test
    void findById() {
        restClient.get().uri("/{id}", 1L)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Employee.class)
                .value(employee -> assertNotNull(employee.getId()))
                .value(employee -> assertNotNull(employee.getName()))
                .value(employee -> assertEquals(1L, employee.getId()));
    }

    @Test
    void findByOrganization() {
        restClient.get().uri("/organization/{organizationId}", 1L)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Employee[].class)
                .value(employees -> assertTrue(employees.length > 0));
    }

    @Test
    void findByDepartment() {
        restClient.get().uri("/department/{departmentId}", 1L)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Employee[].class)
                .value(employees -> assertTrue(employees.length > 0));
    }

    @Test
    void add() {
        Employee employee = Instancio.create(Employee.class);
        restClient.post().body(employee)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Employee.class)
                .value(emp -> assertNotNull(emp.getId()))
                .value(emp -> assertNotNull(emp.getName()));
    }
}
