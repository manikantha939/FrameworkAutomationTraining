package automationtraining.base;

import automationtraining.util.PropertyUtil;

public abstract class AbstractBase {

	protected String getProperty(String key) {
		return PropertyUtil.getProperty(key);
	}
}
