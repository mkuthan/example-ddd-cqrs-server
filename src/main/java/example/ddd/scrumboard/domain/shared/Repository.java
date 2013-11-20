package example.ddd.scrumboard.domain.shared;

public interface Repository<T extends AggregateRoot, ID extends UniqueIdentifier<?>> {

	ID generateId();

	T load(ID id);

	void save(T aggregateEntity);

	void delete(T aggregateEntity);

}
