package co.uk.app.commerce.unittest.users.base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import co.uk.app.commerce.users.application.UsersApplication;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DatabaseSetup(AbstractUnitTest.DATASET)
@SpringBootTest(classes = { UsersApplication.class })
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { AbstractUnitTest.DATASET })
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class AbstractUnitTest {

	protected static final String DATASET = "classpath:dataset.xml";
}
