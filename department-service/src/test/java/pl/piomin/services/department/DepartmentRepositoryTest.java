package pl.piomin.services.department;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.util.Assert;
import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.repository.DepartmentRepository;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentRepositoryTest {

    private static final DepartmentRepository repository = new DepartmentRepository();

    @Test
    @Order(1)
    void testAddDepartment() {
        Department department = new Department(1L, "Test");
        department = repository.add(department);
        Assert.notNull(department, "Department is null.");
        Assert.isTrue(department.getId() == 1L, "Department bad id.");
    }

    @Test
    @Order(2)
    void testFindAll() {
        List<Department> departments = repository.findAll();
        Assert.isTrue(departments.size() == 1, "Departments size is wrong.");
        Assert.isTrue(departments.get(0).getId() == 1L, "Department bad id.");
    }

    @Test
    @Order(3)
    void testFindByOrganization() {
        List<Department> departments = repository.findByOrganization(1L);
        Assert.isTrue(departments.size() == 1, "Departments size is wrong.");
        Assert.isTrue(departments.get(0).getId() == 1L, "Department bad id.");
    }

    @Test
    @Order(4)
    void testFindById() {
        Department department = repository.findById(1L);
        Assert.notNull(department, "Department not found.");
        Assert.isTrue(department.getId() == 1L, "Department bad id.");
    }

}
