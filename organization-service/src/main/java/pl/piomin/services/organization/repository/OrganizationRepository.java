package pl.piomin.services.organization.repository;

import pl.piomin.services.organization.model.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationRepository {

	private List<Organization> organizations = new ArrayList<>();
	
	public Organization add(Organization organization) {
		organization.setId((long) (organizations.size()+1));
		organizations.add(organization);
		return organization;
	}
	
	public Organization findById(Long id) {
		return organizations.stream()
				.filter(a -> a.getId().equals(id))
				.findFirst()
				.orElseThrow();
	}
	
	public List<Organization> findAll() {
		return organizations;
	}
	
}
