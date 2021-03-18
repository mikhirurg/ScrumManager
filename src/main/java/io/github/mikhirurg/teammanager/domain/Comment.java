package io.github.mikhirurg.teammanager.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment_table")
@ToString(of = {"id","name"})
@EqualsAndHashCode(of = {"id"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @JsonView(Views.Id.class)
    private Long id;

    @Column(name = "task_id")
    @NotNull
    @JsonView(Views.Id.class)
    private Long taskId;

    @Column(name = "employee_id")
    @NotNull
    @JsonView(Views.Id.class)
    private Long employeeId;

    @Column(name = "text")
    @NotNull
    @JsonView(Views.Id.class)
    private String text;

    @Column(name = "date")
    @NotNull
    @JsonView(Views.Id.class)
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
