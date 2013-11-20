package example.ddd.scrumboard.domain.shared;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public abstract class UniqueIdentifier<T> {

	private T id;

	public UniqueIdentifier(T id) {
		this.id = requireNonNull(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		@SuppressWarnings("unchecked")
		UniqueIdentifier<T> that = (UniqueIdentifier<T>) obj;

		return Objects.equals(this.id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		return Objects.toString(id);
	}

}
