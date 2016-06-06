package springStart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/config/smashConsole-context.xml",
		"classpath:/META-INF/config/smashConsole-context-persistence.xml",
		"classpath:/META-INF/config/smashConsole-context-security.xml", "classpath:/WEB-INF/smashConsole-servlet.xml" })
@WebAppConfiguration
public class ServiceManagementTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/*
	 * @Configuration
	 * 
	 * @EnableWebMvc public static class TestConfiguration {
	 * 
	 * @Bean public SmashServicesRestContoller smashServiceController() { return
	 * new SmashServicesRestContoller(); }
	 * 
	 * }
	 */

	String instanceName = "192.168.5.2:8080";

	@Test
	public void getAllServices() throws Exception {
		this.mockMvc.perform(get("/rest/getAll").accept("application/json")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0]serviceId").value(1))
				.andExpect(jsonPath("$.[0]serviceName").value("Service1"))
				.andExpect(jsonPath("$.[0]instances").value(IsNull.nullValue()))
				.andExpect(jsonPath("$.[1]serviceId").value(2))
				.andExpect(jsonPath("$.[1]serviceName").value("Service2"))
				.andExpect(jsonPath("$.[1]instances").value(IsNull.nullValue()))

		;
	}

//	@Test
//	public void getInstance_ShouldReturnNoContent() throws Exception {
//		this.mockMvc.perform(get("/rest/{instanceName}/smash/engine", "instanceName").accept("application/json"))
//				.andExpect(status().isNoContent());
//	}

//	@Test
//	public void getInstance_ShouldReturnOk() throws Exception {
//		// String instanceName = "192.168.5.2:8080";
//
//		this.mockMvc.perform(get("/rest/{instanceName}/smash/engine", instanceName)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.instanceId").value(134))
//				.andExpect(jsonPath("$.instanceName").value(instanceName));
//	}

	@Test
	public void getAllInstances_ShoudReturnOk() throws Exception {
		this.mockMvc.perform(get("/rest/smash/engine").accept("application/json")).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0]instanceId").value(138))
				.andExpect(jsonPath("$.[0]instanceName").value("192.168.222.2:8080"))
				.andExpect(jsonPath("$.[0]serviceName").value("Engine"));
	}

	@Test
	public void getLog_ShouldReturnNoContent() throws Exception {
		this.mockMvc.perform(get("/rest/getLog/{instanceId}", 1)).andExpect(status().isNoContent());
	}

	@Test
	public void getLog_ShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/rest/getLog/{instanceId}", "id")).andExpect(status().isBadRequest());
	}

	@Test
	public void getLogs_ShoudReturnOk() throws Exception {
		this.mockMvc.perform(get("/rest/getLog/{instanceId}", 134)).andExpect(status().isOk());
	}

	@Test
	public void startInstance_ShouldReturnNoContent() throws Exception {
		this.mockMvc.perform(put("/rest/{instanceName}/smash/engine/start", "instanceName"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void startInstance_ShouldReturnOk() throws Exception {
		this.mockMvc.perform(put("/rest/{instanceName}/smash/engine/start", instanceName)).andExpect(status().isOk());
	}

	@Test
	public void stopInstance_ShouldReturnNoContent() throws Exception {
		this.mockMvc.perform(put("/rest/{instanceName}/smash/engine/stop", "instanceName"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void stopInstance_ShouldReturnOk() throws Exception {
		this.mockMvc.perform(put("/rest/{instanceName}/smash/engine/stop", instanceName)).andExpect(status().isOk());
	}

	@Test
	public void restartInstance_ShouldReturnNoContent() throws Exception {
		this.mockMvc.perform(put("/rest/{instanceName}/smash/engine/restart", "instanceName"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void restartInstance_ShouldReturnOk() throws Exception {
		this.mockMvc.perform(put("/rest/{instanceName}/smash/engine/restart", instanceName)).andExpect(status().isOk());
	}

	@Test
	public void deleteInstance_ShouldReturnNoContent() throws Exception {
		this.mockMvc.perform(delete("/rest/{instanceName}/smash/engine/delete", "instanceName"))
				.andExpect(status().isNoContent());

	}

}
