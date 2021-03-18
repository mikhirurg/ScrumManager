package io.github.mikhirurg.teammanager.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_table")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
public class Task {

    public enum State {
        OPEN,
        ACTIVE,
        RESOLVED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @JsonView(Views.Id.class)
    private Long id;

    @Column(name = "name")
    @NotNull
    @JsonView(Views.Id.class)
    private String name;

    @Column(name = "description", length = 100000)
    @JsonView(Views.Id.class)
    private String description;

    @Column(name = "state")
    @NotNull
    @JsonView(Views.Id.class)
    private State state;

    @Column(name = "employee_id")
    @Nullable
    @JsonView(Views.Id.class)
    private Long employeeId;

    @Column(name = "update_date")
    @JsonView(Views.Id.class)
    private String updateDate;

    @Column(name = "create_date")
    @NotNull
    @JsonView(Views.Id.class)
    private String createDate;

    @Column(name = "sprint_id")
    @Nullable
    @JsonView(Views.Id.class)
    private Long sprintId;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String accomplishedDate) {
        this.updateDate = accomplishedDate;
    }

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }
}
