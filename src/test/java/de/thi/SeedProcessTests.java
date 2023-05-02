package de.thi;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class SeedProcessTests
{
	@Inject
	@Named("seed")
	Process<? extends Model> seedProcess;

	@Test
	public void shouldRunSeedProcess()
	{
		// given
		assertNotNull(seedProcess);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("varName", "varValue");

		Model model = seedProcess.createModel();
		model.fromMap(parameters);

		ProcessInstance<?> processInstance = seedProcess.createInstance(model);

		// when
		processInstance.start();

		// then
		assertEquals(processInstance.status(), ProcessInstance.STATE_ACTIVE);
	}
}
