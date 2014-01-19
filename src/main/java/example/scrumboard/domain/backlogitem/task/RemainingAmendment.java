package example.scrumboard.domain.backlogitem.task;

import static java.util.Objects.requireNonNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RemainingAmendment {

	@Id
	@GeneratedValue
	private Long entityId;

	@Column(nullable = false)
	private Date effectiveDate;

	@Column(nullable = false)
	private Integer hoursRemaining;

	RemainingAmendment() {
	}

	RemainingAmendment(Date effectiveDate, Integer hoursRemaining) {
		this.effectiveDate = requireNonNull(effectiveDate);
		this.hoursRemaining = requireNonNull(hoursRemaining);
	}

	Date getEffectiveDate() {
		return effectiveDate;
	}

	Integer getHoursRemaining() {
		return hoursRemaining;
	}

}
