package example.ddd.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;

public class ProductBacklogItem {

	private BacklogItemId id;

	private int position;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		ProductBacklogItem that = (ProductBacklogItem) obj;
		return Objects.equals(this.id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return com.google.common.base.Objects.toStringHelper(this).addValue(this.id).addValue(this.position).toString();
	};

	static Ordering<ProductBacklogItem> byPosition() {
		return Ordering.natural().onResultOf(new Function<ProductBacklogItem, Integer>() {

			@Override
			public Integer apply(ProductBacklogItem input) {
				return input.position;
			}
		});
	}

	ProductBacklogItem(BacklogItemId id, int position) {
		this.id = requireNonNull(id);
		this.position = requirePositivePosition(position);
	}

	BacklogItemId getId() {
		return id;
	}

	int getPosition() {
		return position;
	}

	void reorder(int newPosition) {
		this.position = requirePositivePosition(newPosition);
	}

	private int requirePositivePosition(int priority) {
		if (priority < 0) {
			throw new IllegalArgumentException("Position must be positive but was " + priority);
		} else {
			return priority;
		}
	}

}
