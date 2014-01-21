package example.scrumboard.application.api;

import example.scrumboard.application.api.commands.CreateProductCommand;
import example.scrumboard.application.api.commands.PlanBacklogItemCommand;
import example.scrumboard.application.api.commands.ReorderBacklogItemsCommand;
import example.scrumboard.application.api.commands.ScheduleReleaseCommand;
import example.scrumboard.application.api.commands.ScheduleSprintCommand;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.release.ReleaseId;
import example.scrumboard.domain.sprint.SprintId;

public interface ProductService {

	ProductId createProduct(CreateProductCommand command);

	BacklogItemId planBacklogItem(ProductId productId, PlanBacklogItemCommand command);

	void reorderBacklogItems(ReorderBacklogItemsCommand command);

	ReleaseId scheduleRelease(ScheduleReleaseCommand command);

	SprintId scheduleSprint(ScheduleSprintCommand command);

}