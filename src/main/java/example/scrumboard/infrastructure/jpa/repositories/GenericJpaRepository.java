package example.scrumboard.infrastructure.jpa.repositories;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.orm.ObjectRetrievalFailureException;

import example.ddd.AggregateRoot;
import example.ddd.Repository;

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

	protected Class<E> getEntityClass() {
		return entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
