package test.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestGoogleDirectionService.class,
		TestGooglePlacesServices.class, TestLocationUtility.class,TestHiveWebService.class})
public class AllTests {

}
