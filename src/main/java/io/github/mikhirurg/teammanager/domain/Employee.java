package io.github.mikhirurg.teammanager.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "employee_table")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
public class Employee {

    public enum Role {
        TEAM_LEADER,
        LEADER,
        EMPLOYEE
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

    @Column(name = "role")
    @NotNull
    @JsonView(Views.Id.class)
    private Integer role;

    @Column(name = "manager_id")
    @JsonView(Views.Id.class)
    private Long managerId;

    public void update(Employee other) {
        if (other.getManagerId() != null) {
            setManagerId(other.getManagerId());
        }
        if (other.getName() != null) {
            setName(other.getName());
        }
        if (other.getRole() != null) {
            setRole(other.getRole());
        }
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
