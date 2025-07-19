package context;

import java.util.HashMap;
import java.util.Map;

/**
 * Scenario-scoped context for storing temporary data during a single Cucumber
 * scenario.
 * 
 * This class is safe for parallel test execution because PicoContainer injects
 * a new instance per scenario.
 */
public class ScenarioContext {

	private Map<String, Object> scenarioContext;

	public ScenarioContext() {
		scenarioContext = new HashMap<String, Object>();
	}

	public void put(String key, Object value) {
		scenarioContext.put(key, value);
	}

	public Object get(String key) {
		if (!containsKey(key)) {
			System.err.println(String.format("Key '%s' not found in ScenarioContext.", key));
		}

		return scenarioContext.get(key);
	}

	public Boolean containsKey(String key) {
		return scenarioContext.containsKey(key);
	}

}
