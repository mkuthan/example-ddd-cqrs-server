package example.scrumboard.infrastructure.jpa.repositories;

import example.ddd.infrastructure.GenericJpaRepository;
import example.scrumboard.domain.backlog.task.Task;
import example.scrumboard.domain.backlog.task.TaskId;
import example.scrumboard.domain.backlog.task.TaskRepository;

@JpaRepository
public class JpaTaskRepository extends GenericJpaRepository<Task, TaskId> implements TaskRepository {
}
