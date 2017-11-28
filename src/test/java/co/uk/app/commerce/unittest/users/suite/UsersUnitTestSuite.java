package co.uk.app.commerce.unittest.users.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import co.uk.app.commerce.unittest.users.registration.service.RegistrationServiceUnitTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    RegistrationServiceUnitTest.class
})
public class UsersUnitTestSuite {

}
