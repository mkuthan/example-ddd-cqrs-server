package example.ddd.infrastructure;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.orm.ObjectRetrievalFailureException;

import example.ddd.domain.AggregateRoot;
import example.ddd.domain.Repository;

public class GenericJpaRepository<E extends AggregateRoot<K>, K> implements Repository<E, K> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public GenericJpaRepository() {
		this.entityClass = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	public E load(K id) {
		requireNonNull(id);

		E entity = entityManager.find(entityClass, id, LockModeType.OPTIMISTIC);

		if (entity == null) {
			throw new ObjectRetrievalFailureException(entityClass, id);
		}

		return entity;
	}

	@Override
	public void save(E entity) {
		requireNonNull(entity);

		if (entityManager.contains(entity)) {
			entityManager.lock(entity, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		} else {
			entityManager.persist(entity);
		}

		entityManager.flush();
	}

	@Override
	public void delete(E entity) {
		requireNonNull(entity);

		entityManager.remove(entity);
		entityManager.flush();
	}

	@Override
	public long count() {
		StringBuffer queryString = new StringBuffer("SELECT count(e) from ") //
				.append(entityClass.getSimpleName()) //
				.append(" e");

		TypedQuery<Long> query = entityManager.createQuery(queryString.toString(), Long.class);
		return query.getSingleResult();
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
