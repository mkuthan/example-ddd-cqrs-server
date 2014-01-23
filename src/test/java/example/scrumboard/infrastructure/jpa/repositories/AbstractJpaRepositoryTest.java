package example.scrumboard.infrastructure.jpa.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(singleThreaded = true)
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@PersistenceContext
	private EntityManager entityManager;

	protected void clear() {
		entityManager.clear();
	}

	protected Long countEntities(Class<?> entityClass) {
		StringBuffer queryString = new StringBuffer("SELECT count(e) from ") //
				.append(entityClass.getSimpleName()) //
				.append(" e");

		TypedQuery<Long> query = entityManager.createQuery(queryString.toString(), Long.class);
		return query.getSingleResult();
	}
}
