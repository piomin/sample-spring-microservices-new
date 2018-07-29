package pl.piomin.services.organization.repository;

import org.springframework.data.repository.CrudRepository;

import pl.piomin.services.organization.model.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, String> {
	
}
