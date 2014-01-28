package example.scrumboard.infrastructure.jpa.repositories;

import example.scrumboard.domain.backlogitem.task.Task;
import example.scrumboard.domain.backlogitem.task.TaskId;
import example.scrumboard.domain.backlogitem.task.TaskRepository;

@JpaRepository
public class JpaTaskRepository extends GenericJpaRepository<Task, TaskId> implements TaskRepository {
}
