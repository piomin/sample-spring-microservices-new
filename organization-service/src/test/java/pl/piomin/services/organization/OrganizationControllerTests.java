package pl.piomin.services.organization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.piomin.services.organization.model.Organization;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.config.discovery.enabled=false"
    }
)
public class OrganizationControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void findAll() {
        Organization[] o = restTemplate.getForObject("/", Organization[].class);
        assertTrue(o.length > 0);
    }

    @Test
    void findById() {
        Organization o = restTemplate.getForObject("/{id}", Organization.class, 1);
        assertNotNull(o);
        assertNotNull(o.getId());
        assertEquals(1, o.getId());
    }

    @Test
    void add() {
        Organization o = new Organization("Test", "Test1");
        o = restTemplate.postForObject("/", o, Organization.class);
        assertNotNull(o);
        assertNotNull(o.getId());
        assertEquals(3, o.getId());
    }
}
