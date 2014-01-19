package example.scrumboard.domain.sprint;

import static java.util.Objects.requireNonNull;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.google.common.collect.Iterables;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductId;

@Entity
public class Sprint extends AggregateRoot<SprintId> {

	@Embedded
	private ProductId productId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Date beginDate;

	@Column(nullable = false)
	private Date endDate;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "sprint_id", nullable = false, insertable = false, updatable = false)
	private Set<CommitedBacklogItem> backlogItems;

	Sprint(SprintId id, Product product, String name, Date beginDate, Date endDate,
			Set<CommitedBacklogItem> backlogItems) {
		super(id);
		this.productId = requireNonNull(product).getId();
		this.name = requireNonNull(name);
		this.beginDate = requireNonNull(beginDate);
		this.endDate = requireNonNull(endDate);
		this.backlogItems = requireNonNull(backlogItems);

		if (beginDate.after(endDate)) {
			throw new IllegalArgumentException("Begin date must be before end date " + endDate + " but was "
					+ beginDate + ".");
		}
	}

	public ProductId getProductId() {
		return productId;
	}

	public void commitBacklogItem(BacklogItem backlogItem) {
		requireNonNull(backlogItem);
		backlogItem.checkProduct(productId);

		BacklogItemId backlogItemId = backlogItem.getId();
		if (Iterables.any(backlogItems, CommitedBacklogItem.hasId(backlogItemId))) {
			throw new IllegalArgumentException("Backlog item is already commited " + backlogItemId);
		}

		backlogItems.add(new CommitedBacklogItem(backlogItemId));
	}

	public void uncommitBacklogItem(BacklogItem backlogItem) {
		requireNonNull(backlogItem);
		backlogItem.checkProduct(productId);

		BacklogItemId backlogItemId = backlogItem.getId();
		boolean removed = Iterables.removeIf(backlogItems, CommitedBacklogItem.hasId(backlogItemId));
		if (!removed) {
			throw new IllegalArgumentException("Backlog item is not commited " + backlogItemId);
		}
	}

	String getName() {
		return name;
	}

	Date getBeginDate() {
		return beginDate;
	}

	Date getEndDate() {
		return endDate;
	}

	Set<CommitedBacklogItem> getBacklogItems() {
		return backlogItems;
	}

}
