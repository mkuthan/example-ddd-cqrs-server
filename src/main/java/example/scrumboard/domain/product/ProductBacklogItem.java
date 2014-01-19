package example.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Predicate;

import example.scrumboard.domain.backlogitem.BacklogItemId;

@Entity
public class ProductBacklogItem {

	static Predicate<ProductBacklogItem> hasId(final BacklogItemId id) {
		return new Predicate<ProductBacklogItem>() {
			@Override
			public boolean apply(ProductBacklogItem input) {
				return input.getId().equals(id);
			}
		};
	}

	@Id
	@GeneratedValue
	private Long entityId;

	@Embedded
	private BacklogItemId id;

	@Column(nullable = false)
	private Integer position;

	ProductBacklogItem() {
	}

	ProductBacklogItem(BacklogItemId id, Integer position) {
		this.id = requireNonNull(id);
		this.position = requireNonNull(position);
	}

	BacklogItemId getId() {
		return id;
	}

	Integer getPosition() {
		return position;
	}

	void setPosition(Integer position) {
		this.position = position;
	}
}
