package io.github.mikhirurg.teammanager.repo;

import io.github.mikhirurg.teammanager.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Employee, Long> {

}
