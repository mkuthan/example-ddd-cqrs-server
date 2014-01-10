package example.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import example.ddd.domain.ValueObject;
import example.scrumboard.domain.backlog.item.BacklogItemId;

@Entity
public class ProductBacklogItem extends ValueObject {

	private static final long serialVersionUID = 1L;

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

	public BacklogItemId getId() {
		return id;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
}
