package example.scrumboard.domain.release;

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

import example.ddd.AggregateRoot;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductId;

@Entity
public class Release extends AggregateRoot<ReleaseId> {

	@Embedded
	private ProductId productId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Date releaseDate;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "release_id", nullable = false, insertable = false, updatable = false)
	private Set<ScheduledBacklogItem> backlogItems;

	Release() {
	}

	Release(ReleaseId id, Product product, String name, Date releaseDate, Set<ScheduledBacklogItem> backlogItems) {
		super(id);
		this.productId = requireNonNull(product).getId();
		this.name = requireNonNull(name);
		this.releaseDate = requireNonNull(releaseDate);
		this.backlogItems = requireNonNull(backlogItems);
	}

	ProductId getProductId() {
		return productId;
	}

	String getName() {
		return name;
	}

	Date getReleaseDate() {
		return releaseDate;
	}

	Set<ScheduledBacklogItem> getBacklogItems() {
		return backlogItems;
	}

	public void scheduleBacklogItem(BacklogItem backlogItem) {
		// TODO Auto-generated method stub

	}

	public void unscheduleBacklogItem(BacklogItem backlogItem) {
		// TODO Auto-generated method stub

	}

}
