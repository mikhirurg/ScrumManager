package io.github.mikhirurg.teammanager.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "log_table")
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id"})
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @JsonView(Views.Id.class)
    private Long id;

    @Column(name = "date")
    @NotNull
    @JsonView(Views.Id.class)
    private String date;

    @Column(name = "message")
    @NotNull
    @JsonView(Views.Id.class)
    private String message;

    public static Log createLog(String text) {
        Log log = new Log();
        log.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm")));
        log.setMessage(text);
        return log;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
