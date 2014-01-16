package example.scrumboard.application.impl;

import java.sql.Date;

import example.ddd.domain.ApplicationService;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.release.ReleaseId;

@ApplicationService
public class ReleaseServiceImpl {

	public ReleaseId scheduleRelease(String name, Date releaseDate) {
		return null;
	}

	public void scheduleBacklogItem(ReleaseId releaseId, BacklogItemId backlogItemId) {
	}

	public void unscheduleBacklogItem(ReleaseId releaseId, BacklogItemId backlogItemId) {
	}
	
	public void archive(ReleaseId releaseId) {
	}

}
