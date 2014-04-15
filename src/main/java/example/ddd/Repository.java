package example.ddd;

public interface Repository<E extends AggregateRoot<K>, K> {

	E load(K entityId);

	void save(E entity);

	void delete(E entity);

}
