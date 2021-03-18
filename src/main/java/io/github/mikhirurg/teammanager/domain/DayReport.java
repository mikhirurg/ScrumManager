package io.github.mikhirurg.teammanager.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "day_report_table")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
public class DayReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @JsonView(Views.Id.class)
    private Long id;

    @Column(name = "date")
    @NotNull
    @JsonView(Views.Id.class)
    private String date;

    @Column(name = "text", length = 100000)
    @JsonView(Views.Id.class)
    private String text;

    @Column(name = "employee_id")
    @NotNull
    @JsonView(Views.Id.class)
    private Long employeeId;

    public void update(DayReport other) {
        if (other.getDate() != null) {
            setDate(other.getDate());
        }
        if (other.getText() != null) {
            setText(other.getText());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
