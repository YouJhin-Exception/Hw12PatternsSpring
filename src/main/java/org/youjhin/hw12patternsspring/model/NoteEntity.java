package org.youjhin.hw12patternsspring.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.youjhin.hw12patternsspring.enums.Status;
import org.youjhin.hw12patternsspring.enums.Urgency;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "notesTable")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    public NoteEntity() {

    }

    public NoteEntity(String description, Status status, Urgency urgency) {
        this.description = description;
        this.status = status;
        this.urgency = urgency;
    }

    @OneToMany(mappedBy = "workerTask")
    private List<WorkerEntity> workers;


    @Override
    public String toString() {
        return "NoteId = " + id + ", Description Note = " + description + "\n";
    }


}
