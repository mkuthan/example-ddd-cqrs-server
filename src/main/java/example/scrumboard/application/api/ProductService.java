package example.scrumboard.application.api;

import java.util.Date;

import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.release.ReleaseId;
import example.scrumboard.domain.sprint.SprintId;

public interface ProductService {

	ProductId createProduct(CreateProductCommand command);

	BacklogItemId planBacklogItem(ProductId productId, String backlogItemStory);

	void reorderBacklogItems(ProductId productId, BacklogItemId... backlogItemIds);

	ReleaseId scheduleRelease(ProductId productId, String releaseName, Date releaseDate);

	SprintId scheduleSprint(ProductId productId, String sprintName, Date sprintBeginDate, Date sprintEndDate);

}