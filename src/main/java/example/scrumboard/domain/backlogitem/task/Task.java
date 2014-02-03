package example.scrumboard.domain.backlogitem.task;

import static java.util.Objects.requireNonNull;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import example.ddd.AggregateRoot;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemId;

@Entity
public class Task extends AggregateRoot<TaskId> {

	@Embedded
	private BacklogItemId backlogItemId;

	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Integer hoursRemaining;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "task_id", nullable = false, insertable = false, updatable = false)
	@OrderColumn
	private List<RemainingAmendment> remainingAmendments;

	Task() {
	}

	Task(TaskId id, BacklogItem backlogItem, TaskStatus status, String name, String description,
			Integer hoursRemaining, List<RemainingAmendment> remainingAmendments) {
		super(id);
		this.backlogItemId = requireNonNull(backlogItem).getId();
		this.status = requireNonNull(status);
		this.name = requireNonNull(name);
		this.description = requireNonNull(description);
		this.hoursRemaining = requireNonNull(hoursRemaining);
		this.remainingAmendments = requireNonNull(remainingAmendments);
	}

	public void begin() {
		status.begin(this);
	}

	void doBegin() {
		status = TaskStatus.IN_PROGRESS;
	}

	public void finish() {
		status.finish(this);
	}

	void doFinish() {
		status = TaskStatus.DONE;
	}

	public void amendHoursRemaining(Date effectiveDate, Integer hoursRemaing) {
		status.amendHoursRemaining(this, effectiveDate, hoursRemaing);
	}

	void doAmendHoursRemaining(Date effectiveDate, Integer hoursRemaing) {
		RemainingAmendment remainingAmendment = new RemainingAmendment(effectiveDate, hoursRemaing);
		remainingAmendments.add(remainingAmendment);
	}

	BacklogItemId getBacklogItemId() {
		return backlogItemId;
	}

	TaskStatus getStatus() {
		return status;
	}

	String getName() {
		return name;
	}

	String getDescription() {
		return description;
	}

	Integer getHoursRemaining() {
		return hoursRemaining;
	}

	List<RemainingAmendment> getRemainingAmendments() {
		return remainingAmendments;
	}

}
