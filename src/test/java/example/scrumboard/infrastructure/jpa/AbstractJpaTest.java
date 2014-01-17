package example.scrumboard.infrastructure.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(singleThreaded = true)
public abstract class AbstractJpaTest extends AbstractTransactionalTestNGSpringContextTests {

	@PersistenceContext
	private EntityManager entityManager;

	protected void clear() {
		entityManager.clear();
	}
}
