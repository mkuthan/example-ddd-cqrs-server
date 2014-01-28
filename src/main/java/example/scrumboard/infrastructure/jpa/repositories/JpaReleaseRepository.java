package example.scrumboard.infrastructure.jpa.repositories;

import example.scrumboard.domain.release.Release;
import example.scrumboard.domain.release.ReleaseId;
import example.scrumboard.domain.release.ReleaseRepository;

@JpaRepository
public class JpaReleaseRepository extends GenericJpaRepository<Release, ReleaseId> implements ReleaseRepository {
}
