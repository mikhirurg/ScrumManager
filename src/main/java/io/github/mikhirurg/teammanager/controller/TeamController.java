package io.github.mikhirurg.teammanager.controller;

import io.github.mikhirurg.teammanager.domain.Employee;
import io.github.mikhirurg.teammanager.domain.Log;
import io.github.mikhirurg.teammanager.repo.EmployeeRepo;
import io.github.mikhirurg.teammanager.repo.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("teamapi")
public class TeamController {
    private final EmployeeRepo employeeRepo;
    private final LogRepo logRepo;

    @Autowired
    public TeamController(EmployeeRepo employeeRepo, LogRepo logRepo) {
        this.employeeRepo = employeeRepo;
        this.logRepo = logRepo;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee findEmployee(@PathVariable("id") Employee employee) {
        return employee;
    }

    @GetMapping("/employee/{id}/manager")
    public Optional<Employee> findManager(@PathVariable("id") Employee employee) {
        System.out.println(employee.getName());
        return employeeRepo.findById(employee.getManagerId());
    }

    @GetMapping("/employee/{id}/members")
    public List<Employee> findMembers(@PathVariable("id") Employee employee) {
        return employeeRepo.findAll().stream()
                .filter(e -> employee.getId().equals(e.getManagerId()))
                .collect(Collectors.toList());
    }

    @PostMapping("/employee/add")
    public Employee create(@RequestBody Employee employee) {
        logRepo.save(Log.createLog("New employee created! name: " + employee.getName() + ", id: " + employee.getId()));
        return employeeRepo.save(employee);
    }

    @PutMapping("/employee/{id}/edit")
    public Employee edit(@PathVariable("id") Employee employee, @RequestBody Employee newEmployee) {
        System.out.println(employee.getName());
        System.out.println(newEmployee.getName());
        logRepo.save(Log.createLog("Employee edited! name: " + employee.getName() + ", id: " + employee.getId()));
        employee.update(newEmployee);
        return employeeRepo.save(employee);
    }

}
