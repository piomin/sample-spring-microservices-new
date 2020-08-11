import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.util.Assert;
import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.repository.DepartmentRepository;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentRepositoryTest {

    private static DepartmentRepository repository = new DepartmentRepository();

    @Test
    public void testAddDepartment() {
        Department department = new Department(1L, "Test");
        department = repository.add(department);
        Assert.notNull(department, "Department is null.");
        Assert.isTrue(department.getId() == 1L, "Department bad id.");
    }

    @Test
    public void testFindAll() {
        List<Department> departments = repository.findAll();
        Assert.isTrue(departments.size() == 1, "Departments size is wrong.");
        Assert.isTrue(departments.get(0).getId() == 1L, "Department bad id.");
    }

    @Test
    public void testFindByOrganization() {
        List<Department> departments = repository.findByOrganization(1L);
        Assert.isTrue(departments.size() == 1, "Departments size is wrong.");
        Assert.isTrue(departments.get(0).getId() == 1L, "Department bad id.");
    }

    @Test
    public void testFindById() {
        Department department = repository.findById(1L);
        Assert.notNull(department, "Department not found.");
        Assert.isTrue(department.getId() == 1L, "Department bad id.");
    }

}
