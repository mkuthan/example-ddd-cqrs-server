package example.ddd.scrumboard.domain.product;

import lombok.NonNull;
import lombok.Value;

@Value
public class ProductId {

	@NonNull
	String id;

}
