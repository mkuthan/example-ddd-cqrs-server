package example.scrumboard.infrastructure.jpa.repositories;

import example.ddd.infrastructure.GenericJpaRepository;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.item.BacklogItemRepository;

@JpaRepository
public class JpaBacklogItemRepository extends GenericJpaRepository<BacklogItem, BacklogItemId> implements
		BacklogItemRepository {
}
