package example.scrumboard.application.api;

import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.release.ReleaseId;

public interface ReleaseService {

	void scheduleBacklogItem(ReleaseId releaseId, BacklogItemId backlogItemId);

	void unscheduleBacklogItem(ReleaseId releaseId, BacklogItemId backlogItemId);

	void archive(ReleaseId releaseId);

}