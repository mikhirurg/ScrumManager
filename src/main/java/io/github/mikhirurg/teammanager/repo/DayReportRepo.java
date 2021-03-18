package io.github.mikhirurg.teammanager.repo;

import io.github.mikhirurg.teammanager.domain.DayReport;
import io.github.mikhirurg.teammanager.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayReportRepo extends JpaRepository<DayReport, Long> {
}
