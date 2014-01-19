package example.scrumboard.domain.sprint;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import example.scrumboard.domain.product.Product;

@Component
public class SprintFactory {

	public Sprint create(Product product, String name, Date beginDate, Date endDate) {
		SprintId id = new SprintId(UUID.randomUUID().toString());
		Set<CommitedBacklogItem> backlogItems = new HashSet<>();

		return new Sprint(id, product, name, beginDate, endDate, backlogItems);
	}
}
