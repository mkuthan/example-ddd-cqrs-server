package example.scrumboard.domain.sprint;

import static java.util.Objects.requireNonNull;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.backlogitem.BacklogItem;
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
	@JoinColumn(name = "sprint_id", nullable = false)
	private Set<CommitedBacklogItem> backlogItems;

	Sprint(SprintId id, Product product, String name, Date beginDate, Date endDate,
			Set<CommitedBacklogItem> backlogItems) {
		super(id);
		this.productId = requireNonNull(product).getId();
		this.name = requireNonNull(name);
		this.beginDate = requireNonNull(beginDate);
		this.endDate = requireNonNull(endDate);
		this.backlogItems = requireNonNull(backlogItems);
	}

	public void commitBacklogItem(BacklogItem backlogItem) {
		// TODO Auto-generated method stub
	}

	public void uncommitBacklogItem(BacklogItem backlogItem) {
		// TODO Auto-generated method stub
	}

	ProductId getProductId() {
		return productId;
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
