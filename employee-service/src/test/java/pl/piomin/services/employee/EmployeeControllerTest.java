package pl.piomin.services.employee;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentationConfigurer;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.piomin.services.employee.controller.EmployeeController;
import pl.piomin.services.employee.model.Employee;
import pl.piomin.services.employee.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureRestDocs
public class EmployeeControllerTest {

	@MockBean
	EmployeeRepository repository;
	@Autowired
	MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();

	@TestConfiguration
	static class CustomizationConfiguration implements RestDocsMockMvcConfigurationCustomizer {

		@Override
		public void customize(MockMvcRestDocumentationConfigurer configurer) {
//			configurer.snippets().withTemplateFormat(TemplateFormats.markdown())
			configurer.operationPreprocessors()
				.withRequestDefaults(prettyPrint())
				.withResponseDefaults(prettyPrint());
		}
		
		@Bean
		public RestDocumentationResultHandler restDocumentation() {
			return MockMvcRestDocumentation.document("{method-name}");
		}
	}

	@Before
	public void setUp() {
		Employee e = new Employee(1L, 1L, "John Smith", 33, "Developer");
		e.setId(1L);
		when(repository.add(Mockito.any(Employee.class))).thenReturn(e);
		when(repository.findById(1L)).thenReturn(e);
	}

	@Test
	public void addPerson() throws JsonProcessingException, Exception {
		Employee employee = new Employee(1L, 1L, "John Smith", 33, "Developer");
		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(employee)))
				.andExpect(status().isOk())
				.andDo(document("{method-name}/", requestFields(
						fieldWithPath("id").description("Employee id").ignored(),
						fieldWithPath("organizationId").description("Employee's organization id"),
						fieldWithPath("departmentId").description("Employee's department id"),
						fieldWithPath("name").description("Employee's name"),
						fieldWithPath("age").description("Employee's age"),
						fieldWithPath("position").description("Employee's position inside organization")
					)));
	}
	
	@Test
	public void findPersonById() throws JsonProcessingException, Exception {
		this.mockMvc.perform(get("/{id}", 1).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(document("{method-name}/", responseFields(
					fieldWithPath("id").description("Employee id"),
					fieldWithPath("organizationId").description("Employee's organization id"),
					fieldWithPath("departmentId").description("Employee's department id"),
					fieldWithPath("name").description("Employee's name"),
					fieldWithPath("age").description("Employee's age"),
					fieldWithPath("position").description("Employee's position inside organization")
				), pathParameters(parameterWithName("id").description("Employee id"))));
	}

}
