package io.github.mikhirurg.teammanager.repo;

import io.github.mikhirurg.teammanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
