package example.ddd.scrumboard.domain.shared;

public interface Repository<T extends AggregateRoot<ID>, ID> {

	ID generateId();

	T load(ID id);

	void save(T aggregateRoot);

	void delete(T aggregateRoot);

}
