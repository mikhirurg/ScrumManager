package io.github.mikhirurg.teammanager.repo;

import io.github.mikhirurg.teammanager.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepo extends JpaRepository<Sprint, Long> {

}
