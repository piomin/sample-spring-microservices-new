package pl.piomin.services.department;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import pl.piomin.services.department.client.EmployeeClient;
import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.model.Employee;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.config.discovery.enabled=false"
    }
)
@AutoConfigureRestTestClient
public class DepartmentAppTests {

    @Autowired
    RestTestClient restClient;
    @MockitoBean
    EmployeeClient employeeClient;

    @Test
    void findAll() {
        restClient.get()
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Department[].class)
                .value(departments -> assertTrue(departments.length > 0));
    }

    @Test
    void findById() {
        restClient.get().uri("/{id}", 1L)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Department.class)
                .value(department -> assertNotNull(department.getId()))
                .value(department -> assertNotNull(department.getName()))
                .value(department -> assertEquals(1L, department.getId()));
    }

    @Test
    void findByOrganization() {
        restClient.get().uri("/organization/{organizationId}", 1L)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Department[].class)
                .value(departments -> assertTrue(departments.length > 0));
    }

    @Test
    void findByOrganizationWithEmployees() {
        Mockito.when(employeeClient.findByDepartment(Mockito.anyLong()))
                .thenReturn(Instancio.ofList(Employee.class).create());
        restClient.get().uri("/organization/{organizationId}/with-employees", 1L)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Department[].class)
                .value(departments -> assertTrue(departments.length > 0));
    }

    @Test
    void add() {
        Department department = Instancio.create(Department.class);
        restClient.post().body(department)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Department.class)
                .value(dep -> assertNotNull(dep.getId()))
                .value(dep -> assertNotNull(dep.getName()));
    }
}
