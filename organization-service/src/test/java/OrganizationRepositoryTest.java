import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.util.Assert;
import pl.piomin.services.organization.model.Organization;
import pl.piomin.services.organization.repository.OrganizationRepository;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrganizationRepositoryTest {

    private static OrganizationRepository repository = new OrganizationRepository();

    @Test
    public void testAddOrganization() {
        Organization organization = new Organization("Test", "Test Street");
        organization = repository.add(organization);
        Assert.notNull(organization, "Organization is null.");
        Assert.isTrue(organization.getId() == 1L, "Organization bad id.");
    }

    @Test
    public void testFindAll() {
        List<Organization> organizations = repository.findAll();
        Assert.isTrue(organizations.size() == 1, "Organizations size is wrong.");
        Assert.isTrue(organizations.get(0).getId() == 1L, "Organization bad id.");
    }

    @Test
    public void testFindById() {
        Organization organization = repository.findById(1L);
        Assert.notNull(organization, "Organization not found.");
        Assert.isTrue(organization.getId() == 1L, "Organization bad id.");
    }

}
