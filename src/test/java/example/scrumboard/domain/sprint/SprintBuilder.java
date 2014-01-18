package example.scrumboard.domain.sprint;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductBuilder;

public class SprintBuilder {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("YYYY-MM-DD");

	private SprintId id = new SprintId("any id");

	private Product product = new ProductBuilder().build();

	private String name = "any name";

	private Date beginDate = DATE_TIME_FORMATTER.parseDateTime("2010-01-01").toDate();

	private Date endDate = DATE_TIME_FORMATTER.parseDateTime("2010-01-14").toDate();

	private Set<CommitedBacklogItem> backlogItems = new HashSet<>();

	public SprintBuilder withId(SprintId id) {
		this.id = id;
		return this;
	}

	public SprintBuilder withProduct(Product product) {
		this.product = product;
		return this;
	}

	public SprintBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public SprintBuilder withBeginDate(String beginDate) {
		this.beginDate = DATE_TIME_FORMATTER.parseDateTime(beginDate).toDate();
		return this;
	}

	public SprintBuilder withEndDate(String endDate) {
		this.endDate = DATE_TIME_FORMATTER.parseDateTime(endDate).toDate();
		return this;
	}

	public SprintBuilder addBacklogItem(CommitedBacklogItem backlogItem) {
		this.backlogItems.add(backlogItem);
		return this;
	}

	public Sprint build() {
		return new Sprint(id, product, name, beginDate, endDate, backlogItems);
	}
}
