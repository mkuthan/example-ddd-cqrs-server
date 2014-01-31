package example.scrumboard.domain.backlogitem;

public enum StoryPoints {

	ZERO(0), ONE(1), TWO(2), THREE(3), FIVE(5), EIGHT(8), THIRTEEN(13);

	private int value;

	private StoryPoints(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
