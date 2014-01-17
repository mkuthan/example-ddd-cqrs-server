package example.scrumboard.domain.sprint;

import java.sql.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class SprintFactory {

	public Sprint create(String name, Date beginDate, Date endDate) {
		SprintId id = new SprintId(UUID.randomUUID().toString());

		return new Sprint(id, null);
	}
}
