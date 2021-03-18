package io.github.mikhirurg.teammanager.repo;

import io.github.mikhirurg.teammanager.domain.SprintReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintReportRepo extends JpaRepository<SprintReport, Long> {
}
