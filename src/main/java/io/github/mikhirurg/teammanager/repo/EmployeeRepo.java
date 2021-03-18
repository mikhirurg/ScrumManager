package io.github.mikhirurg.teammanager.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.mikhirurg.teammanager.domain.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
