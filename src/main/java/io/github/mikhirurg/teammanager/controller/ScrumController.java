package io.github.mikhirurg.teammanager.controller;

import io.github.mikhirurg.teammanager.domain.*;
import io.github.mikhirurg.teammanager.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("scrumapi")
public class ScrumController {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
    private final SprintRepo sprintRepo;
    private final TaskRepo taskRepo;
    private final CommentRepo commentRepo;
    private final DayReportRepo dayReportRepo;
    private final SprintReportRepo sprintReportRepo;
    private final LogRepo logRepo;

    @Autowired
    public ScrumController(SprintRepo sprintRepo, TaskRepo taskRepo, CommentRepo commentRepo,
                           DayReportRepo dayReportRepo, SprintReportRepo sprintReportRepo, LogRepo logRepo) {
        this.sprintRepo = sprintRepo;
        this.taskRepo = taskRepo;
        this.commentRepo = commentRepo;
        this.dayReportRepo = dayReportRepo;
        this.sprintReportRepo = sprintReportRepo;
        this.logRepo = logRepo;
    }

    @GetMapping("/sprints")
    public List<Sprint> getSprints() {
        return sprintRepo.findAll();
    }

    @GetMapping("/sprint/{id}")
    public Sprint findSprint(@PathVariable("id") Sprint sprint) {
        return sprint;
    }

    @PostMapping("/sprint/add")
    public Sprint createSprint(@RequestBody Sprint sprint) {
        System.out.println(sprint.getName());
        logRepo.save(Log.createLog("New sprint created! (name: " + sprint.getName() + ", id: " + sprint.getId() + ")"));
        return sprintRepo.save(sprint);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskRepo.findAll();
    }

    @GetMapping("/task/{id}")
    public Task findTask(@PathVariable("id") Task task) {
        return task;
    }

    @PostMapping("/task/add")
    public Task createTask(@RequestBody Task task) {
        task.setCreateDate(LocalDateTime.now().format(dateTimeFormatter));
        task.setUpdateDate(LocalDateTime.now().format(dateTimeFormatter));
        logRepo.save(Log.createLog("New task created! (name: " + task.getName() + ", id: " + task.getId() + ")"));
        System.out.println(task.getName());
        return taskRepo.save(task);
    }

    @GetMapping("/task/{id}/assign/{employeeId}")
    public Task assignTask(@PathVariable("id") Task task, @PathVariable("employeeId") Employee employee) {
        task.setUpdateDate(LocalDateTime.now().format(dateTimeFormatter));
        task.setEmployeeId(employee.getId());
        task.setState(Task.State.ACTIVE);
        logRepo.save(Log.createLog("Task (name: " + task.getName() + ", id: " + task.getId() +
                ") assigned to Employee (name: " + employee.getName() + ", id: " + employee.getId() + ")"));
        return taskRepo.save(task);
    }

    @GetMapping("/task/{id}/complete")
    public Task completeTask(@PathVariable("id") Task task) {
        task.setState(Task.State.RESOLVED);
        logRepo.save(Log.createLog("Task (name: " + task.getName() + ", id: " + task.getId() + ") completed!"));
        return taskRepo.save(task);
    }

    @GetMapping("/tasks/byId/{id}")
    public Optional<Task> getTaskById(@PathVariable("id") Long id) {
        return taskRepo.findById(id);
    }

    @GetMapping("/tasks/byCreateDate/{startDate}/{endDate}")
    public List<Task> getTasksByStartDay(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        return taskRepo.findAll().stream()
                .filter(task -> (task.getCreateDate() != null &&
                        LocalDateTime.parse(task.getCreateDate(), dateTimeFormatter)
                                .isAfter(LocalDateTime.parse(startDate, dateTimeFormatter)) &&
                        LocalDateTime.parse(task.getCreateDate(), dateTimeFormatter)
                                .isBefore(LocalDateTime.parse(endDate, dateTimeFormatter))))
                .sorted(Comparator.comparing(Task::getCreateDate))
                .collect(Collectors.toList());
    }

    @GetMapping("/tasks/byLastUpdate/{startDate}/{endDate}")
    public List<Task> getTaskByLastUpdate(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        return taskRepo.findAll().stream()
                .filter(task -> (task.getUpdateDate() != null &&
                        LocalDateTime.parse(task.getUpdateDate(), dateTimeFormatter)
                                .isAfter(LocalDateTime.parse(startDate, dateTimeFormatter)) &&
                        LocalDateTime.parse(task.getUpdateDate(), dateTimeFormatter)
                                .isBefore(LocalDateTime.parse(endDate, dateTimeFormatter))))
                .sorted(Comparator.comparing(Task::getUpdateDate))
                .collect(Collectors.toList());
    }

    @GetMapping("/tasks/byEmployee/{id}")
    public List<Task> getTaskByEmployeeId(@PathVariable("id") Long id) {
        return taskRepo.findAll().stream()
                .filter(task -> id.equals(task.getEmployeeId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/comments/{taskId}")
    public List<Comment> getCommentsByTaskId(@PathVariable("taskId") Task task) {
        return commentRepo.findAll().stream()
                .filter(comment -> task.getId().equals(comment.getTaskId()))
                .sorted(Comparator.comparing(Comment::getDate))
                .collect(Collectors.toList());
    }

    @PostMapping("/comments/add")
    public Comment createComment(@RequestBody Comment comment) {
        comment.setDate(LocalDateTime.now().format(dateTimeFormatter));
        logRepo.save(Log.createLog("Comment (id: " + comment.getId() + ") created!"));
        return commentRepo.save(comment);
    }

    @PostMapping("/dayReport/add")
    public DayReport createDayReport(@RequestBody DayReport report) {
        report.setDate(LocalDateTime.now().format(dateTimeFormatter));
        logRepo.save(Log.createLog("Day report (id: " + report.getId() + ") created!"));
        return dayReportRepo.save(report);
    }

    @GetMapping("/dayReports")
    public List<DayReport> getDayReportList() {
        return dayReportRepo.findAll();
    }

    @GetMapping("/sprintReports")
    public List<SprintReport> getSprintReportList() {
        return sprintReportRepo.findAll();
    }

    @PostMapping("/sprintReport/add/{sprintId}")
    public SprintReport createSprintReport(@RequestBody SprintReport report, @PathVariable(name = "sprintId") Sprint sprint) {
        report.setSprintId(sprint.getId());
        report.setClosed(false);
        logRepo.save(Log.createLog("Sprint report (id: " + report.getId() + ") created!"));
        return sprintReportRepo.save(report);
    }

    @GetMapping("/dayReport/{id}")
    public Optional<DayReport> getDayReportById(@PathVariable(name = "id") Long id) {
        return dayReportRepo.findById(id);
    }

    @GetMapping("/sprintReport/{id}")
    public Optional<SprintReport> getSprintReportById(@PathVariable(name = "id") Long id) {
        return sprintReportRepo.findById(id);
    }

    @GetMapping("/sprintReport/{id}/close")
    public SprintReport closeSprintReport(@PathVariable("id") SprintReport report) {
        report.setClosed(true);
        logRepo.save(Log.createLog("Sprint report (id: " + report.getId() + ") is closed!"));
        return sprintReportRepo.save(report);
    }

    @PutMapping("/dayReport/{id}/edit")
    public DayReport editDayReport(@RequestParam(name = "id") DayReport dayReport, @RequestBody DayReport newDayReport) {
        dayReport.update(newDayReport);
        logRepo.save(Log.createLog("Day report (id: " + dayReport.getId() + ") edited!"));
        return dayReportRepo.save(dayReport);
    }

    @GetMapping("/dayReport/byEmployeeId/{id}")
    public List<DayReport> getDayReportByEmployeeId(@PathVariable(name = "id") Long id) {
        return dayReportRepo.findAll().stream().filter(e -> e.getEmployeeId().equals(id)).collect(Collectors.toList());
    }

    @PutMapping("/sprintReport/{id}/edit")
    public SprintReport editSprintReport(@RequestParam(name = "id") SprintReport sprintReport,
                                         @RequestBody SprintReport newSprintReport) {
        sprintReport.update(newSprintReport);
        logRepo.save(Log.createLog("Sprint report (id: " + sprintReport.getId() + ") edited!"));
        return sprintReportRepo.save(sprintReport);
    }

    @GetMapping("/logs")
    public List<Log> getLogs() {
        return logRepo.findAll();
    }

    @GetMapping("/log/{id}")
    public Log getLogsById(@PathVariable("id") Log log) {
        return log;
    }

    @GetMapping("/log/{startDate}/{endDate}")
    public List<Log> getLogInInterval(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        return logRepo.findAll().stream()
                .filter(log -> (log.getDate() != null &&
                        LocalDateTime.parse(log.getDate(), dateTimeFormatter).isAfter(LocalDateTime.parse(startDate, dateTimeFormatter)) &&
                        LocalDateTime.parse(log.getDate(), dateTimeFormatter).isBefore(LocalDateTime.parse(endDate, dateTimeFormatter))))
                .sorted(Comparator.comparing(Log::getDate))
                .collect(Collectors.toList());
    }
}
