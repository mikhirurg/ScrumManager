package io.github.mikhirurg.teammanager.repo;

import io.github.mikhirurg.teammanager.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<Log, Long> {
}
