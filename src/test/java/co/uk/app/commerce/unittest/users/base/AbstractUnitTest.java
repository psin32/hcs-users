package co.uk.app.commerce.unittest.users.base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import co.uk.app.commerce.users.application.UsersApplication;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { UsersApplication.class })
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class AbstractUnitTest {

}
