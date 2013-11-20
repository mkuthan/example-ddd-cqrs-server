package example.ddd.scrumboard.domain.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(of = "id")
@ToString
public class ProductBacklogItem {

	@NonNull
	@Getter(AccessLevel.PACKAGE)
	private BacklogItemId id;

	@NonNull
	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private Integer position;

}
