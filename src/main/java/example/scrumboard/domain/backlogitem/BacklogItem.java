package example.scrumboard.domain.backlogitem;

import static java.util.Objects.requireNonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import example.ddd.AggregateRoot;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.release.Release;
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintId;

@Entity
public class BacklogItem extends AggregateRoot<BacklogItemId> {

	@Embedded
	private ProductId productId;

	@Column(nullable = false)
	private String story;

	@Embedded
	@AttributeOverride(name = "id", column = @Column(nullable = true))
	private SprintId sprintId;

	BacklogItem() {
	}

	BacklogItem(BacklogItemId id, Product product, String story) {
		super(id);
		this.productId = requireNonNull(product).getId();
		this.story = requireNonNull(story);
	}

	BacklogItem(BacklogItemId id, Product product, String story, Sprint sprint) {
		this(id, product, story);
		if (sprint != null) {
			this.sprintId = sprint.getId();
		}
	}

	public void commitToSprint(Sprint sprint) {
		requireNonNull(sprint);
		checkProduct(sprint.getProductId());

		if (isCommited()) {
			if (isEqualTo(sprint)) {
				// do nothing
				return;
			} else {
				uncommitFromSprint();
			}
		}

		this.sprintId = sprint.getId();
		register(new BacklogItemCommited(getId(), sprint.getId()));
	}

	public void uncommitFromSprint(Sprint sprint) {
		requireNonNull(sprint);
		checkProduct(sprint.getProductId());

		if (!isCommited()) {
			throw new IllegalArgumentException("Backlog item " + getId() + " is not commited.");
		}

		if (!isEqualTo(sprint)) {
			throw new IllegalArgumentException("Backlog item " + getId() + " is commited to " + sprintId
					+ " but expected " + sprint.getId() + ".");
		}

		uncommitFromSprint();
	}

	public void scheduleToRelease(Release release) {
		// TODO Auto-generated method stub
	}

	public void unscheduleFromRelease(Release release) {
		// TODO Auto-generated method stub
	}

	public void assignStoryPoints(StoryPoints storyPoints) {
		// TODO Auto-generated method stub
	}

	public void assignPriority(Priority priority) {
		// TODO Auto-generated method stub
	}

	public void checkProduct(ProductId productId) {
		if (!this.productId.equals(productId)) {
			throw new IllegalArgumentException("Products do not match, product was " + productId + " but expected "
					+ this.productId + ".");
		}
	}

	ProductId getProductId() {
		return productId;
	}

	SprintId getSprintId() {
		return sprintId;
	}

	private boolean isCommited() {
		return sprintId != null;
	}

	private boolean isEqualTo(Sprint sprint) {
		return sprintId.equals(sprint.getId());
	}

	private void uncommitFromSprint() {
		SprintId sprintId = this.sprintId;
		this.sprintId = null;
		register(new BacklogItemUncommited(getId(), sprintId));
	}

}
