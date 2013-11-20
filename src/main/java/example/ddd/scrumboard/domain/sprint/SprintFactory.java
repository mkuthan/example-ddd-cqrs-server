package example.ddd.scrumboard.domain.sprint;

public class SprintFactory {

	private SprintRepository sprintRepository;

	public SprintFactory(SprintRepository repository) {
		this.sprintRepository = repository;
	};

	public Sprint create() {
		SprintId id = sprintRepository.generateId();

		return new Sprint(id);
	}
}
