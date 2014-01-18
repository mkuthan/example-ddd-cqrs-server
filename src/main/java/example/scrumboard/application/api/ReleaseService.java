package example.scrumboard.application.api;

import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.release.ReleaseId;

public interface ReleaseService {

	void scheduleBacklogItem(ReleaseId releaseId, BacklogItemId backlogItemId);

	void unscheduleBacklogItem(ReleaseId releaseId, BacklogItemId backlogItemId);

	void archive(ReleaseId releaseId);

}