package springStart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import dyntech.smashconsole.domain.Job;
import dyntech.smashconsole.dto.JobDetailDto;
import dyntech.smashconsole.util.Cron;
import oracle.net.aso.j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/config/smashConsole-context.xml",
		"classpath:/META-INF/config/smashConsole-context-persistence.xml",
		"classpath:/META-INF/config/smashConsole-context-security.xml", "classpath:/WEB-INF/smashConsole-servlet.xml" })
@WebAppConfiguration
public class JobSchedulerTest {

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
	public void getAllJobs() throws Exception {
		this.mockMvc.perform(get("/rest/getAllJobs").accept("application/json")).andExpect(status().isOk());
	}

	/*
	 * 0: { "idJob": 6 "name": "Job6" "jobType": "it.iks.smash.ProcedureJob6"
	 * "description": "desc6" "arguments": "arg2arg3" "mailingList":
	 * "niko@gmail.com, mario@gmail.com, maki@gmail.com" "triggerType": "cron"
	 * "cronTrigger": "0 30 7 ? * 5" "alerts": "none" "status": false
	 * "startDate": 1458388794006 "endDate": 1458475200006 "weekInterval": 0
	 * "jobParam": null "lastJobLog": { "idLog": 44 "idJob": 6 "logDate":
	 * 1459253279000 "description": "Log description" "type": "Error" }- }
	 */

	@Test
	public void getJob_ShouldReturnNoContent() throws Exception {
		this.mockMvc.perform(get("/rest/getJob/{jobId}", 555).accept("application/json"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void getJob_ShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/rest/getJob/{jobId}", "id").accept("application/json"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getJob_ShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/rest/getJob/{jobId}", 1).accept("application/json")).andExpect(status().isOk());
	}

	
	@Test
	public void getJobLog_ShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/rest/getJobHistory/{jobId}", "id").accept("application/json"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getJobLog_ShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/rest/getJobHistory/{jobId}", 1).accept("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void getJobDetail_ShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(get("/rest/getJobDetail/{jobId}", "id").accept("application/json"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getJobDetail_ShouldReturnNoContent() throws Exception {
		this.mockMvc.perform(get("/rest/getJobDetail/{jobId}", 555).accept("application/json"))
				.andExpect(status().isNoContent());
	}

	@Test
	public void getJobDetail_ShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/rest/getJobDetail/{jobId}", 1).accept("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	public void updateJobDetail_ShouldReturnOk() throws Exception {
			
			JobDetailDto jobDetailDto = new JobDetailDto();
			Job job = new Job();
			Cron cron = new Cron();
			
			job.setIdJob(1);
			job.setName("jana");
			job.setJobType("it.iks.smash.ProcedureJob");
			job.setDescription("desc1");
			job.setArguments("arg2arg3");
			job.setMailingList("mario@gmail.com, maki@gmail.com");
			job.setTriggerType("cron");
			job.setCronTrigger("0 30 7 * * ?");
			job.setAlerts("end");
			job.setStatus(false);
			job.setStartDate(Timestamp.valueOf("2015-09-06 21:22:2.222"));
			job.setEndDate(Timestamp.valueOf("2015-09-07 21:22:2.022"));
			job.setWeekInterval(0);
			job.setJobParam(null);
			job.setLastJobLog(null);			
			cron.setStartTime("9:00");
			cron.setRecurrencePattern("Daily");
			cron.setEveryMinutes(0);
			cron.setMonths(null);
			cron.setDayOfMonth(0);
			cron.setDays(null);
			jobDetailDto.setCron(cron);
			jobDetailDto.setJob(job);
						
			this.mockMvc.perform(put("/rest/updateJobDetail")
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(TestUtils.convertObjectToJsonBytes(jobDetailDto))
					)
//					.accept("application/json"))
					.andExpect(status().isOk());
		}

	@Test
	public void changeJobStatus_ShouldReturnBadRequest() throws Exception {
		this.mockMvc.perform(put("/rest/changeJobStatus/{jobId}/{status}", "id", "active").accept("application/json"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void changeJobStatus_ShouldReturnNoContent() throws Exception {
		this.mockMvc.perform(put("/rest/changeJobStatus/{jobId}/{status}", 555, "active").accept("application/json"))
				.andExpect(status().isNoContent());
	}
	
	
	@Test
	public void changeJobStatus_ShouldReturnOk() throws Exception {
		this.mockMvc.perform(put("/rest/changeJobStatus/{jobId}/{status}", 1, "active").accept("application/json"))
				.andExpect(status().isOk());
	}
	
	
}
