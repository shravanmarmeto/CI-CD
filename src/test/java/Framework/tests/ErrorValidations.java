package Framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import Framework.TestComponent.Baseclass;
import Framework.TestComponent.Retry;

public class ErrorValidations extends Baseclass {

	@Test (groups = {"Errorhandling"}, retryAnalyzer = Retry.class)
	public void errorloginErrorValidations() {

		lp.login("buvanshetty@gmail.com", "Shravan");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
}