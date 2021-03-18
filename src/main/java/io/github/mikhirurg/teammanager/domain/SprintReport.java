package io.github.mikhirurg.teammanager.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "sprint_report_table")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
public class SprintReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @JsonView(Views.Id.class)
    private Long id;

    @Column(name = "sprint_id")
    @NotNull
    @JsonView(Views.Id.class)
    private Long sprintId;

    @Column(name = "text", length = 100000)
    @JsonView(Views.Id.class)
    private String text;

    @Column(name = "is_closed", length = 100000)
    @NotNull
    @JsonView(Views.Id.class)
    private Boolean isClosed;

    public void update(SprintReport other) {
        if (other.getSprintId() != null) {
            setSprintId(other.getSprintId());
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

    public Long getSprintId() {
        return sprintId;
    }

    public void setSprintId(Long sprintId) {
        this.sprintId = sprintId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        isClosed = closed;
    }
}
