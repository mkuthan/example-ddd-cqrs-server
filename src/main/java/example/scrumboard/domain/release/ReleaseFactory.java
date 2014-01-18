package example.scrumboard.domain.release;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import example.scrumboard.domain.product.Product;

@Component
public class ReleaseFactory {

	public Release create(Product product, String name, Date releaseDate) {
		ReleaseId id = new ReleaseId(UUID.randomUUID().toString());
		Set<ScheduledBacklogItem> backlogItems = new HashSet<>();

		return new Release(id, product, name, releaseDate, backlogItems);
	}
}
