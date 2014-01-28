package example.scrumboard.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.ApplicationService;
import example.scrumboard.application.api.ReleaseService;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.backlogitem.BacklogItemRepository;
import example.scrumboard.domain.release.Release;
import example.scrumboard.domain.release.ReleaseId;
import example.scrumboard.domain.release.ReleaseRepository;

@ApplicationService
public class ReleaseServiceImpl implements ReleaseService {

	@Autowired
	private ReleaseRepository releaseRepository;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	@Override
	public void scheduleBacklogItem(ReleaseId releaseId, BacklogItemId backlogItemId) {
		Release release = releaseRepository.load(releaseId);

		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);
		backlogItem.scheduleToRelease(release);

		release.scheduleBacklogItem(backlogItem);

		releaseRepository.save(release);
	}

	@Override
	public void unscheduleBacklogItem(ReleaseId releaseId, BacklogItemId backlogItemId) {
		Release release = releaseRepository.load(releaseId);

		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);
		backlogItem.unscheduleFromRelease(release);

		release.unscheduleBacklogItem(backlogItem);

		releaseRepository.save(release);
	}

	@Override
	public void archive(ReleaseId releaseId) {
		// TODO Auto-generated method stub
	}

}
