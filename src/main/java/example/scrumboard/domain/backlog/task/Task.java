package example.scrumboard.domain.backlog.task;

import static java.util.Objects.requireNonNull;

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

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemId;

@Entity
public class Task extends AggregateRoot<TaskId> {

	@Embedded
	private BacklogItemId backlogItemId;

	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer hoursRemaining;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "task_id", nullable = false)
	@OrderColumn
	private List<RemainingAmendment> remainingAmendments;

	Task() {
	}

	Task(TaskId id, BacklogItem backlogItem, TaskStatus status, String name, Integer hoursRemaining,
			List<RemainingAmendment> remainingAmendments) {
		super(id);
		this.backlogItemId = requireNonNull(backlogItem).getId();
		this.status = requireNonNull(status);
		this.name = requireNonNull(name);
		this.hoursRemaining = requireNonNull(hoursRemaining);
		this.remainingAmendments = requireNonNull(remainingAmendments);
	}

	public void start() {
		status.start(this);
	}

	public void finish() {
		status.finish(this);
	}

	void doStart() {
		status = TaskStatus.IN_PROGRESS;
	}

	void doFinish() {
		status = TaskStatus.DONE;
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

	Integer getHoursRemaining() {
		return hoursRemaining;
	}

	List<RemainingAmendment> getRemainingAmendments() {
		return remainingAmendments;
	}

}
