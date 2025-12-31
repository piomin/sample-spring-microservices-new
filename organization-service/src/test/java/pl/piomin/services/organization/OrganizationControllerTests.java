package pl.piomin.services.organization;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import pl.piomin.services.organization.client.DepartmentClient;
import pl.piomin.services.organization.model.Department;
import pl.piomin.services.organization.model.Organization;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.config.discovery.enabled=false"
    }
)
@AutoConfigureRestTestClient
public class OrganizationControllerTests {

    @Autowired
    RestTestClient restClient;
    @MockitoBean
    DepartmentClient departmentClient;

    @Test
    void findAll() {
        restClient.get()
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Organization[].class)
                .value(organizations -> assertTrue(organizations.length > 0));
    }

    @Test
    void findById() {
        restClient.get().uri("/{id}", 1L)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Organization.class)
                .value(organization -> assertNotNull(organization.getId()))
                .value(organization -> assertNotNull(organization.getName()))
                .value(organization -> assertEquals(1L, organization.getId()));
    }

    @Test
    void findByIdWithDepartments() {
        Mockito.when(departmentClient.findByOrganization(Mockito.anyLong()))
                .thenReturn(Instancio.ofList(Department.class).create());
        restClient.get().uri("/{id}/with-departments", 1L)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Organization.class)
                .value(organization -> assertNotNull(organization.getId()))
                .value(organization -> assertFalse(organization.getDepartments().isEmpty()));
    }

    @Test
    void add() {
        Organization department = Instancio.create(Organization.class);
        restClient.post().body(department)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Organization.class)
                .value(org -> assertNotNull(org.getId()))
                .value(org -> assertNotNull(org.getName()));
    }
}
