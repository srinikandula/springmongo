package test.com.beakyn;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.beakyn.FactualClient;

public class TestFactualClient extends AbstractControllerIntegrationTest {
	@Autowired
	private FactualClient client;

	@Test
	public void testLoadTestClient() {
		client.initialize();
	}

}
