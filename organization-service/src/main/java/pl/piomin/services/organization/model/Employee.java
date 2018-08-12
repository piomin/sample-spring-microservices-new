package pl.piomin.services.organization.model;

public class Employee {

	private Long id;
	private String name;
	private String position;
	private Long departmentId;

	public Employee() {

	}

	public Employee(String name, String position, Long departmentId) {
		super();
		this.name = name;
		this.position = position;
		this.departmentId = departmentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", position=" + position + "]";
	}

}
